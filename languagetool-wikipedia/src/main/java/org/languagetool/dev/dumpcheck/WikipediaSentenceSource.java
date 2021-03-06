/* LanguageTool, a natural language style checker
 * Copyright (C) 2013 Daniel Naber (http://www.danielnaber.de)
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301
 * USA
 */
package org.languagetool.dev.dumpcheck;

import org.languagetool.Language;
import org.languagetool.dev.wikipedia.SwebleWikipediaTextFilter;
import org.languagetool.dev.wikipedia.TextMapFilter;
import org.languagetool.tokenizers.Tokenizer;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Provides access to the sentences of a Wikipedia XML dump. Note that
 * conversion exceptions are logged to STDERR and are otherwise ignored.
 * 
 * To get an XML dump, download {@code pages-articles.xml.bz2} from
 * <a href="http://download.wikimedia.org/backup-index.html">http://download.wikimedia.org/backup-index.html</a>, e.g.
 * {@code http://download.wikimedia.org/dewiki/latest/dewiki-latest-pages-articles.xml.bz2}.
 * @since 2.4
 */
class WikipediaSentenceSource extends SentenceSource {

  private final TextMapFilter textFilter = new SwebleWikipediaTextFilter();
  private final XMLEventReader reader;
  private final Tokenizer sentenceTokenizer;
  private final List<WikipediaSentence> sentences;
  private final Language language;

  WikipediaSentenceSource(InputStream xmlInput, Language language) {
    super(language);
    try {
      XMLInputFactory factory = XMLInputFactory.newInstance();
      reader = factory.createXMLEventReader(xmlInput);
      sentenceTokenizer = language.getSentenceTokenizer();
      sentences = new ArrayList<>();
      this.language = language;
    } catch (XMLStreamException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public boolean hasNext() {
    try {
      fillSentences();
    } catch (XMLStreamException e) {
      throw new RuntimeException(e);
    }
    return sentences.size() > 0;
  }

  @Override
  public Sentence next() {
    try {
      fillSentences();
      if (sentences.size() == 0) {
        throw new NoSuchElementException();
      }
      WikipediaSentence wikiSentence = sentences.remove(0);
      String url = "http://" + language.getShortName() + ".wikipedia.org/wiki/" + wikiSentence.title;
      return new Sentence(wikiSentence.sentence, getSource(), wikiSentence.title, url);
    } catch (XMLStreamException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public String getSource() {
    return "wikipedia";
  }

  private void fillSentences() throws XMLStreamException {
    String title = null;
    while (sentences.size() == 0 && reader.hasNext()) {
      XMLEvent event = reader.nextEvent();
      if (event.getEventType() == XMLStreamConstants.START_ELEMENT) {
        String elementName = event.asStartElement().getName().getLocalPart();
        if (elementName.equals("title")) {
          event = reader.nextEvent();
          title = event.asCharacters().getData();
        } else if (elementName.equals("text")) {
          handleTextElement(title);
        }
      }
    }
  }

  private void handleTextElement(String title) throws XMLStreamException {
    XMLEvent event = reader.nextEvent();
    StringBuilder sb = new StringBuilder();
    while (event.isCharacters()) {
      sb.append(event.asCharacters().getData());
      event = reader.nextEvent();
    }
    try {
      String textToCheck = textFilter.filter(sb.toString()).getPlainText();
      for (String sentence : sentenceTokenizer.tokenize(textToCheck)) {
        if (acceptSentence(sentence)) {
          sentences.add(new WikipediaSentence(sentence, title));
        }
      }
    } catch (Exception e) {
      System.err.println("Could not extract text, skipping document: " + e.toString() + ", full stacktrace follows:");
      e.printStackTrace();
    }
  }

  private class WikipediaSentence {
    final String sentence;
    final String title;
    WikipediaSentence(String sentence, String title) {
      this.sentence = sentence;
      this.title = title;
    }
  }
}
