/* LanguageTool, a natural language style checker 
 * Copyright (C) 2012 Jaume Ortolà i Font
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
package org.languagetool.rules.ca;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.languagetool.AnalyzedSentence;
import org.languagetool.AnalyzedTokenReadings;
import org.languagetool.rules.Category;
import org.languagetool.rules.RuleMatch;

/**
 * This rule checks the use of pronominal/non pronominal verbs.
 *   
 * @author Jaume Ortolà i Font
 */
public class ReflexiveVerbsRule extends CatalanRule {

  /**
   * Patterns
   */
  
//List of only pronominal verbs from GDLC (eliminats: assolar, enfundar, burlar, traslluir)
  private static final Pattern VERBS_PRONOMINALS = Pattern.compile("auto.+|fugar|abacallanar|abalançar|ablenar|aborrallonar|abotifarrar|abrinar|abromar|abstenir|acagallonar|acanyar|acarcanyar|acarnissar|acatarrar|aciutadanar|aclocar|acopar|acorriolar|adir|adonar|adormissar|afal·lerar|afarrossar|afeccionar|aferrallar|aferrissar|aferrussar|agallinar|agarbir|agarrofar|agemolir|agenollar|agotzonar|aiguabarrejar|allocar|alçurar|amatinar|amelar|amigar|amoixir|amoltonar|amotar|amullerar|amunionar|antullar|aparroquianar|aparroquiar|aperduar|apergaminar|apiadar|aponentar|apropinquar|apugonar|arguellar|arrapinyar|arrasir|arravatar|arraïmar|arrepapar|arrepenjar|arrepetellar|arrigolar|arrodir|arrogar|arrossar|arruar|assemblar|assocarrar|atendar|atenir|atorrentar|atrafegar|atrevir|avencar|avidolar|avinençar|balbar|balcar|balir|balmar|bescomptar|boirar|boixar|botinflar|bromar|cagaferrar|candir|capbaixar|capmassar|captenir|cariar|carnificar|carpir|coalitzar|colltrencar|collvinclar|compenetrar|condoldre|condolir|congraciar|contorçar|contrapuntar|contòrcer|corcorcar|coresforçar|cornuar|corruixar|crisalidar|desafeccionar|desalenar|desamorar|desaparroquiar|desapassionar|desaplegar|desavenir|desbocar|descantar|descarar|descontrolar|descovar|desdubtar|desempallegar|desenrojolar|desentossudir|desfeinar|desmemoriar|desnodrir|despondre|despreocupar|dessolidaritzar|desteixinar|desvagar|desvergonyir|desviure|dignar|embarbussar|embascar|embessonar|embordeir|embordir|emborrascar|emborrossar|embotifarrar|embotzegar|embromallar|embromar|embroquerar|emmainadar|emmalurar|emmalurir|emmarar|emmarranar|emmatar|emmigranyar|emmorronar|emmurriar|empassar|empassolar|empegueir|empenyalar|empescar|empillocar|empinyar|empiocar|empitarrar|emplomissar|emplujar|emportar|encabotar|encabritar|encalmar|encalostrar|encelar|encinglar|encirar|encistar|enclaperar|encolerir|encordar|encruar|endoblir|endur|enfarfollar|enfaristolar|enfavar|enfereir|enferotgir|enferritjar|enfugir|enfurrunyar|enfutimar|enfutismar|engelabrir|engolfar|engorgar|engripar|enguerxinar|enllagrimar|enlleganyar|enlleir|ennavegar|enneguitar|enquistar|enrinxar|enseriosir|ensobecar|entonyinar|entossudir|entotsolar|entreabaltir|entrebadar|entrebatre|entrebesar|entrecavalcar|entredevorar|entreferir|entreforcar|entrematar|entremetre|entremirar|entrenyorar|entresaludar|entreseguir|entresoldar|entretocar|entretzenar|entrigar|envidreir|envidriar|envolar|enxautar|esbafar|esbafegar|esbatussar|esblamar|esbojarrar|esborneiar|esbromar|escabridar|escamotar|escanyellar|escanyolir|escanyussar|escapolar|escapolir|escarcanyar|escarramicar|escarrassar|escarxofar|escatifenyar|esconillar|escorporar|escullar|escunçar|esfarinar|esfetgegar|esforçar|esgargamellar|esgatinyar|esgolar|esguimbar|esllanguir|esllavissar|esperitar|espitellar|espitxar|espollinar|espoltrar|esporcellar|espotonar|esprimatxar|esquifir|esquitllar|estilar|estritllar|esvedellar|esventegar|esvomegar|etiolar|extralimitar|extravasar|extravenar|gamar|gaspar|gatinyar|gaubar|gloriar|grifar|immiscir|indigestar|industriar|innivar|insolentar|insurgir|intersecar|inveterar|irèixer|jactar|juramentar|lateritzar|llufar|malfiar|malfixar|migrolar|mofar|mullerar|neulir|obstinar|octubrar|olivar|pellobrir|pellpartir|pelltrencar|penedir|penjolar|pollar|prosternar|queixar|querar|querellar|quillar|ramificar|rancurar|realegrar|rebel·lar|rebordeir|refiar|repanxolar|repapar|repetellar|reressagar|resclosir|ressagar|ressentir|revenjar|salinar|suïcidar|tinyar|tolir|transvestir|traspostar|trufar|vanagloriar|vanagloriejar|vanar|vantar|vergonyar|xautar");
  private static final Pattern NO_VERBS_PRONOMINALS = Pattern.compile("atendre|escollir|assolir|autofinançar|autografiar|automatitzar|autoritzar");
  //Eliminats: témer
  private static final Pattern VERBS_NO_PRONOMINALS = Pattern.compile("baixar|caure|callar|marxar|albergar|olorar|seure");
  private static final Pattern VERBS_NO_PRONOMINALS_IMPERSONALS = Pattern.compile("caure|callar|marxar");
  private static final Pattern VERBS_NO_PRONOMINALS_IMPERSONALS2 = Pattern.compile("témer|albergar|baixar");
  private static final Pattern NO_VERBS_NO_PRONOMINALS = Pattern.compile("segar");
  private static final Pattern VERBS_MOVIMENT = Pattern.compile("anar|pujar|venir");
  private static final Pattern VERBS_NO_MOVIMENT = Pattern.compile("vendre");
  private static final Pattern VERBS_SOVINT_AMB_COMPLEMENT = Pattern.compile("deixar|fer|veure|costar");
  private static final Pattern VERBS_DEIXAR_FER = Pattern.compile("deixar|fer");
  private static final Pattern VERBS_PORTAR_DUR = Pattern.compile("portar|dur");
  private static final Pattern VERB_PORTAR = Pattern.compile("portar");
  //list of potentially pronominal verbs from GDLC (eliminat: estudiar)
  private static final Pattern VERBS_POTENCIALMENT_PRONOMINALS = Pattern.compile("abaixar|abandonar|abarrocar|abellir|abismar|abissar|ablamar|ablanir|abocar|aboldronar|abonançar|abonar|abonir|abonyegar|abordar|abraonar|abraçar|abrivar|abroquerar|abrusar|absentar|abstraure|abstreure|aburgesar|acabar|acalar|acalorar|acantonar|acarrerar|acastellanar|acatalanar|accelerar|acetificar|acidificar|aclarir|aclimatar|aclivellar|aclucar|acoblar|acollir|acollonir|acomiadar|acomodar|acomplexar|acomplir|aconductar|aconsellar|acontentar|acopar|acoquinar|acordar|acorruar|acostar|acostumar|acotar|acotxar|acovardir|acreditar|acréixer|acubar|acubillar|acudir|acugular|acuitar|acular|acumular|acusar|adaptar|adargar|adherir|adjudicar|adollar|adolorir|adondar|adormir|adossar|adotzenar|adreçar|adscriure|adunar|afalconar|afanyar|afartar|afeblir|afectar|afermar|aferrar|afigurar|afilar|afilerar|afiliar|afillar|afinar|aflaquir|afligir|aflonjar|afluixar|afogar|afollar|afrancesar|afrevolir|afuar|afusar|agabellar|agafar|agarbar|agarbonar|agitar|aglomerar|aglutinar|agombolar|agostejar|agradar|agregar|agremiar|agreujar|agrir|agrisar|agrumar|aguantar|aguditzar|aigualir|airejar|aixecar|aixoplugar|ajaure|ajaçar|ajeure|ajornalar|ajudar|ajuntar|ajupir|ajustar|alabar|alarmar|alcalinitzar|alcoholitzar|alegrar|alentir|aliar|alimentar|alinear|allarar|allargar|allargassar|allerar|alleugerir|alleujar|alliberar|alligar|allistar|allitar|allotjar|allunyar|alterar|alzinar|alçar|amagar|amagrir|amanerar|amanir|amansar|amansir|amassar|ambientar|americanitzar|amistançar|amistar|amollar|amorar|amorosir|amorrar|amorriar|amotinar|amoïnar|amuntegar|anastomitzar|angoixar|anguniejar|animar|anomenar|anticipar|apagar|apaivagar|apanyar|aparellar|apariar|apartar|aparèixer|apassionar|apercebre|apilotar|apinyar|apitrar|aplanar|aplaçar|aplicar|apocar|apoderar|aposentar|apostar|apostemar|apregonar|aprendre|apressar|aprimar|aprofitar|apropar|apropiar|aprovisionar|aproximar|apujar|apuntalar|aquedar|aquietar|aquilotar|arborar|arbrar|arcar|argollar|aristocratitzar|armar|arquejar|arraconar|arramadar|arrambar|arramellar|arranjar|arrapar|arraulir|arrear|arrecerar|arredossar|arreglar|arrelar|arremangar|arremolinar|arremorar|arrenglerar|arreplegar|arrestar|arribar|arrimar|arriscar|arrissar|arrodonir|arromangar|arrombollar|arronsar|arrossegar|arrufar|arrugar|arruïnar|articular|asfixiar|assabentar|assaonar|assecar|assegurar|assentar|assenyalar|asserenar|assessorar|asseure|assimilar|associar|assolar|assolellar|assossegar|assotar|astorar|atabalar|ataconar|atalaiar|atandar|atansar|atapeir|atardar|atavellar|aterrir|aterrossar|atipar|atiplar|atonir|atorrollar|atracar|atribolar|atribuir|atrinxerar|atrofiar|atropellar|atrotinar|aturar|avalotar|avançar|avarar|avariar|avenir|aventurar|avergonyir|avesar|aviar|aviciar|avidar|avivar|avorrir|aïllar|aïrar|badar|balancejar|balandrejar|baldar|banyar|barallar|barrejar|basar|basquejar|bastar|batre|befar|bellugar|beneficiar|bleir|blocar|bolcar|bombar|bonificar|botir|brindar|brossar|bufar|buidar|burocratitzar|cabrejar|cabussar|cagar|calar|calmar|calçar|campar|cansar|cap|capalçar|capbussar|capficar|capgirar|captar|captrencar|caracteritzar|caragirar|carbonar|carbonatar|carbonitzar|cardar|cargolar|carregar|cartejar|casar|cascar|cenyir|cerciorar|cicatritzar|circumscriure|clamar|classificar|clavar|clivellar|cloure|coagular|cobrir|colar|colgar|colltorçar|colltòrcer|colrar|coltellejar|col·lapsar|col·legiar|col·locar|comanar|combinar|compadir|compaginar|compatir|compensar|complementar|complexificar|complicar|complir|complànyer|compondre|comportar|comprendre|comprimir|comprometre|compungir|comunicar|concentrar|concertar|conciliar|concordar|concretar|condemnar|condensar|conduir|confabular|confederar|confessar|confinar|confirmar|confitar|conformar|congelar|congestionar|conglomerar|conglutinar|congratular|congregar|congriar|conhortar|conjuminar|conjunyir|conjurar|connaturalitzar|consagrar|conscienciar|consentir|conservar|consolar|consolidar|constipar|consumir|contagiar|contaminar|contemperar|contenir|contorbar|contornar|contradir|contraposar|contreure|controlar|convertir|convèncer|corbar|corcar|cordar|coronar|corporificar|corregir|correspondre|corrompre|corsecar|cotitzar|covar|crebantar|cremar|creure|criar|crispar|cucar|cuidar|cuixatrencar|curar|curullar|damnar|debatre|decantar|decidir|declarar|decuplicar|decurvar|dedicar|defendre|defensar|definir|deformar|defugir|degradar|deixar|deixatar|deixondar|deixondir|deixuplinar|delectar|delir|delitar|denudar|departir|depauperar|depilar|deportar|depositar|depravar|deprimir|depurar|derivar|desabillar|desabonar|desabrigar|desacalorar|desacoblar|desaconductar|desaconduir|desacordar|desacostumar|desacreditar|desadherir|desaferrar|desafinar|desagafar|desagermanar|desagradar|desagregar|desajustar|desalinear|desamarrar|desamigar|desamistançar|desamorrar|desanar|desanimar|desaparellar|desapariar|desaparroquianar|desaplicar|desapropiar|desar|desarborar|desarmar|desarramadar|desarrambar|desarranjar|desarrapar|desarreglar|desarregussar|desarrelar|desarrengar|desarrenglar|desarrenglerar|desarrimar|desarrissar|desarromangar|desarrufar|desarrugar|desarticular|desassossegar|desatansar|desatapeir|desatendar|desavesar|desaveïnar|desballestar|desbaratar|desbarbar|desbarrar|desbordar|desbrancar|desbraonar|descabalar|descabdellar|descabellar|descalcificar|descalçar|descaminar|descantellar|descarbonatar|descarbonitzar|descarburar|descargolar|descarnar|descarregar|descarrerar|descartar|descastellanitzar|descatalanitzar|descelerar|descentrar|descenyir|desclassar|desclavar|descloure|descoagular|descobrir|descolgar|descollar|descolorar|descolorir|descol·locar|descompassar|descompensar|descompondre|descomprometre|descomptar|desconceptuar|desconcertar|desconfortar|descongelar|descongestionar|desconhortar|desconjuntar|desconnectar|descoratjar|descordar|descosir|descotxar|descrostar|descular|desdaurar|desdelitar|desdenyar|desdibuixar|desdinerar|desdir|desdoblar|desdoblegar|deseixir|deselectritzar|desembabaiar|desembadalir|desembadocar|desemballestar|desemboirar|desembolcallar|desembolcar|desembolicar|desembotir|desembotjar|desembotornar|desemboçar|desembravir|desembrocar|desembromallar|desembromar|desembullar|desembussar|desembutllofar|desemmandrir|desemmurriar|desempallar|desempastar|desemperesir|desempernar|desempipar|desempobrir|desempolainar|desempolsar|desempolvorar|desenamorar|desencadenar|desencaixar|desencalimar|desencalitjar|desencallar|desencaminar|desencantar|desencaparrar|desencapotar|desencaputxar|desencarar|desencarcarar|desencarranquinar|desencartonar|desencastar|desencaterinar|desencauar|desencavalcar|desencavallar|desencebar|desencerclar|desencercolar|desencimbellar|desencisar|desenclavar|desencoblar|desencolar|desencongir|desencoratjar|desencorbar|desencordillar|desencrespar|desencrostar|desendegar|desendeutar|desendogalar|desendolcir|desendollar|desendropir|desenfadar|desenfadeir|desenfarfegar|desenfellonir|desenferrissar|desenfetgegar|desenfilar|desenfitar|desenflocar|desenfocar|desenfrenar|desenfuriar|desenfurismar|desengandulir|desenganxar|desenganyar|desengatjar|desengavanyar|desengomar|desengormandir|desengorronir|desengreixar|desengrescar|desengruixir|desengrutar|desenguantar|desenguerxir|desenllaminir|desenllaçar|desenlleganyar|desenllepolir|desenllorar|desenlluernar|desenllustrar|desennuegar|desennuvolar|desenquadernar|desenquadrar|desenquimerar|desenrampar|desenredar|desenrederar|desenrolar|desenrotllar|desensabonar|desensenyorir|desensonyar|desensopir|desensuperbir|desentaular|desentelar|desentendre|desentenebrar|desentenebrir|desenterbolir|desenterrar|desentestar|desentortolligar|desentrampar|desentranyar|desentravessar|desentrecuixar|desentrenar|desentristir|desentumir|desentusiasmar|desenutjar|desenvelar|desenvernissar|desenvescar|desenvolupar|desenyorar|desequilibrar|desertitzar|desesmar|desesperançar|desesperar|desespessir|desestancar|desestanyar|desestovar|desfaixar|desfaiçonar|desfanatitzar|desfardar|desfasar|desfermar|desferrar|desficiar|desficiejar|desfigurar|desfilar|desflorir|desfocar|desfogar|desfonar|desfrarar|desfrenar|desfrunzir|desfullar|desganar|desgastar|desgavellar|desglaçar|desgraciar|desgranar|desgruixar|desguarnir|desguerxar|desguitarrar|deshabitar|deshabituar|deshidratar|deshumanitzar|desigualar|desil·lusionar|desimantar|desincorporar|desincrustar|desinfatuar|desinflamar|desinflar|desinhibir|desintegrar|desinteressar|desintoxicar|desionitzar|desjunyir|deslligar|deslliurar|desllodrigar|desllogar|deslloriguerar|deslluir|desllustrar|desmagnetitzar|desmaiar|desmallar|desmanegar|desmaquillar|desmarcar|desmembrar|desmillorar|desmoralitzar|desmorriar|desmudar|desmuntar|desnacionalitzar|desnaturar|desniar|desnierar|desnivellar|desnuar|desnucar|desobligar|desobstruir|desocupar|desorbitar|desordenar|desorganitzar|desorientar|despacientar|desparar|desparellar|despariar|despassar|despenjar|despentinar|despenyar|despersonalitzar|despertar|despintar|despistar|despitar|desplaçar|desplegar|desplomar|despoblar|despolir|desposseir|desprendre|desprestigiar|desprisar|despullar|despuntar|desrengar|desroentar|dessaborir|dessagnar|dessecar|dessolar|dessoldar|dessonillar|dessoterrar|dessuar|dessucar|destacar|destapar|destarotar|destemprar|destenyir|desteular|destintar|destorçar|destravar|destrempar|destrenar|destriar|destrossar|destòrcer|desunglar|desunir|desusar|desvariar|desvariejar|desvesar|desvestir|desvetllar|desviar|desvincular|desvitrificar|detenir|deteriorar|determinar|deturar|devaluar|dialitzar|dibuixar|diferenciar|difondre|diftongar|difuminar|dignificar|dilatar|diluir|dipositar|dirigir|disbauxar|disciplinar|disculpar|disfressar|disgregar|disgustar|dislocar|disparar|dispersar|disposar|disputar|disseminar|dissimilar|dissipar|dissociar|dissoldre|distanciar|distendre|distingir|distreure|distribuir|diversificar|divertir|dividir|divorciar|divulgar|doblar|doblegar|doctorar|documentar|doldre|domesticar|domiciliar|dominar|donar|dopar|dreçar|drogar|dubtar|dulcificar|duplicar|dutxar|eclipsar|efectuar|efeminar|eixamar|eixamenar|eixamorar|eixamplar|eixancar|eixancarrar|eixarrancar|eixarreir|eixorivir|eixugar|electritzar|electrocutar|elevar|elidir|emancipar|embabaiar|embadalir|embadocar|embajanir|embalar|embalbar|embalbir|embancar|embarbollar|embarcar|embardissar|embarracar|embarrancar|embarranquinar|embarrar|embarumar|embarzerar|embasardir|embassar|embastardir|embellir|embeure|embicar|emblanquir|emblavir|embofegar|embogir|emboirar|embolicar|emborbollar|emborratxar|emboscar|embossar|embotinar|embotir|emboçar|embrancar|embravir|embretolir|embriagar|embrocar|embrollar|embromar|embrossar|embrunir|embrutar|embrutir|embullar|embussar|embutllofar|embutxacar|emmagrir|emmalaltir|emmaleir|emmallar|emmandrir|emmarcir|emmaridar|emmascarar|emmatxucar|emmerdar|emmerdissar|emmetzinar|emmirallar|emmotllar|emmudir|emmusteir|emmustigar|emocionar|empadronar|empal·lidir|empantanar|empantanegar|empanxonar|empapatxar|emparar|emparaular|emparentar|emparrar|empastellar|empastifar|empastissar|empatxar|empedreir|empeguntar|empellar|empeltar|empenyorar|emperesir|emperlar|empernar|empetitir|empilar|empinar|empipar|empitjorar|empitrar|empixonar|emplenar|emplomallar|empobrir|empolainar|empolistrar|empolsar|empolsegar|empolsimar|empolsinar|empolvorar|empoquir|emporcar|emporprar|empotingar|emprendre|emprenyar|emprovar|enagrir|enamorar|enamoriscar|enarborar|enarbrar|enarcar|enardir|enasprar|enasprir|encabassar|encabir|encaboriar|encadarnar|encadenar|encaixar|encalbir|encalimar|encalitjar|encallar|encallir|encambrar|encamellar|encaminar|encamisar|encantar|encaparrar|encapellar|encaperonar|encaperullar|encaperutxar|encapirotar|encapotar|encapsular|encapullar|encaputxar|encaramel·lar|encarar|encarbonar|encarir|encarnar|encarranquinar|encarregar|encarrerar|encarrilar|encartonar|encasquetar|encastellar|encauar|encavallar|encegar|encendre|encepar|encertir|encetar|encimbellar|enciriar|enclaustrar|enclotar|encloure|encoblar|encofurnar|encoixir|encomanar|enconar|enconcar|encongir|encontrar|encoratjar|encorbar|encordar|encotillar|encotxar|encovar|encrespar|encreuar|encrostar|encrostimar|encrostissar|encruelir|endarreriar|endarrerir|endegar|endentar|endenyar|enderrocar|endeutar|endinsar|endogalar|endolcir|endolentir|endossar|endropir|endurir|enemistar|enervar|enfadar|enfadeir|enfangar|enfarfegar|enfarinar|enfastidir|enfastijar|enfellonir|enfervorir|enfetgegar|enfigassar|enfilar|enfistular|enfitar|enflocar|enflorar|enfondir|enfonsar|enfonyar|enforfoguir|enforinyar|enfortir|enfosquir|enfredar|enfredolicar|enfredorar|enfredorir|enfrontar|enfuriar|enfurir|enfurismar|engabiar|engalavernar|engallar|engallardir|engallir|engallofir|engalonar|engalvanir|enganar|engandulir|enganxar|enganyar|engatar|engatjar|engelosir|enginjolar|enginyar|engiponar|englotir|engolar|engolir|engordir|engorjar|engormandir|engorronir|engrandir|engreixar|engrescar|engrevir|engroguir|engronsar|engronyar|engrossir|engruixar|engruixir|engrutar|enguantar|enguerxir|enherbar|enjoiar|enjoiellar|enjoncar|enjullar|enlairar|enllacar|enllaminir|enllangorir|enllardar|enllardissar|enllaçar|enllefernar|enllefiscar|enllepissar|enllepolir|enllestir|enlletgir|enllistar|enllorar|enllordar|enllotar|enllustrar|ennegrir|ennoblir|ennovar|ennuegar|ennuvolar|enorgullar|enquadrar|enquibir|enquimerar|enrabiar|enramar|enrampar|enrancir|enrarir|enrasar|enravenar|enredar|enrederar|enrederir|enrellentir|enretirar|enrevenxinar|enriallar|enrigidir|enrinxolar|enriquir|enrobustir|enrocar|enrogir|enrolar|enronquir|enrosar|enrossir|enrotllar|enrullar|enrunar|ensabonar|ensagnar|ensalivar|ensangonar|enseguir|ensenyorir|ensonyar|ensopegar|ensopir|ensordir|ensorrar|ensotar|ensulsir|ensuperbir|entaforar|entatxonar|entaular|entebeir|entebionar|entelar|entendre|entendrir|entenebrar|entenebrir|enterbolir|enterrar|enterrossar|entestar|entollar|entonar|entornar|entortellar|entortolligar|entrampar|entrapar|entravessar|entrebancar|entregar|entregirar|entrellaçar|entrelligar|entremesclar|entrenar|entretenir|entreveure|entrevistar|entristar|entristir|entumir|enturar|entusiasmar|enutjar|envanir|envellir|envellutar|enverdir|enverinar|envermellir|envescar|enviar|envigorir|envilir|environar|enviscar|enviscolar|envitricollar|envoltar|enxarxar|enxiquir|enyorar|equilibrar|equivaler|equivocar|erigir|eriçar|errar|esbadiar|esbadinar|esbadocar|esbalair|esbaldir|esbaldregar|esbandir|esbardellar|esbargir|esbarriar|esbarzerar|esberlar|esbocinar|esboirar|esboldregar|esbombar|esbombolar|esborifar|esborrar|esborrifar|esborronar|esbotifarrar|esbotzar|esbrancar|esbraonar|esbraveir|esbullar|escabellar|escabellonar|escabotar|escaldar|escaldufar|escalfar|escalfeir|escalivar|escalonar|escamarlar|escamnar|escampar|escandalitzar|escantellar|escantonar|escanyar|escapar|escarmentar|escarrabillar|escarxar|escaure|escindir|esclafar|esclafassar|esclarir|esclerosar|escolar|escoltar|escometre|escondir|escotar|escridar|escridassar|escrostar|escrostissar|escrostonar|escruixir|escuar|escudar|escuixar|escular|escurçar|escórrer|esdernegar|esdevenir|esduir|esfacelar|esfereir|esfilagarsar|esfondrar|esfreixurar|esfullar|esfumar|esgallar|esgardissar|esgarrar|esgarrifar|esgarrinxar|esgarrinyar|esgarronar|esgavellar|esglaonar|esgotar|esgratinyar|esguardar|esguerrar|esllenegar|esllomar|esmadeixar|esmalucar|esmenar|esmicar|esmicolar|esmolar|esmorrellar|esmorronar|esmortir|esmunyir|esmussar|espalmar|espantar|espanyolitzar|espaordir|espargir|esparpallar|esparpillar|esparracar|esparverar|espassar|espatllar|espaventar|espavilar|especejar|especialitzar|espedaçar|espellifar|espellir|espellissar|espenyar|esperançar|esperar|espesseir|espessir|espicassar|espigar|espinar|espitrar|esplaiar|esplugar|espolsar|espoltrir|esponjar|esporuguir|esposar|esprémer|espuar|espuntar|espunyir|espuçar|esqueixar|esquerar|esquerdar|esquerdillar|esquerdissar|esquinçar|esquitxar|esquivar|est|estabilitzar|establir|estacionar|estalviar|estamordir|estancar|estandarditzar|estantolar|estanyar|estarrufar|estellar|estendre|estepitzar|estilitzar|estimbar|estintolar|estirar|estireganyar|estiuar|estontolar|estovar|estrangeritzar|estranyar|estratificar|estrenar|estressar|estretir|estrinxolar|estripar|estroncar|estropellar|estrènyer|estubar|estufar|esvair|esvalotar|esventar|esvorar|esvorellar|eternitzar|europeïtzar|evadir|evaporar|exacerbar|exaltar|examinar|exasperar|excedir|excitar|exclamar|excloure|exculpar|excusar|exercitar|exfoliar|exhalar|exhaurir|exhibir|exiliar|eximir|exornar|expandir|expatriar|explicar|exposar|expressar|extasiar|extenuar|exterioritzar|extingir|extraviar|extremar|faixar|familiaritzar|fanatitzar|fastiguejar|fatigar|federar|felicitar|feminitzar|ferir|fiar|ficar|figurar|filtrar|fingir|firar|fixar|flagel·lar|florir|folrar|foraviar|forcar|forjar|formalitzar|formar|fortificar|fossilitzar|fotre|fraccionar|fracturar|fragmentar|francesitzar|franquejar|fregar|fregir|frisar|fumar|fundar|gabar|gastar|gaudir|gelar|generalitzar|gestar|ginyar|girar|gitar|glaçar|gloriejar|governar|graduar|gramaticalitzar|gratar|gratular|gravar|grecitzar|grillar|gronxar|gronxejar|gronxolar|guanyar|guardar|guarir|guarnir|guerxar|guiar|guillar|habituar|hebraïtzar|hel·lenitzar|hemodialitzar|herniar|hibridar|hidratar|hissar|honorar|honrar|horripilar|horroritzar|hostatjar|humanitzar|humiliar|humitejar|identificar|igualar|il·luminar|il·lusionar|il·lustrar|imaginar|immergir|immolar|impacientar|implicar|imposar|impressionar|imprimir|impurificar|incarcerar|incendiar|inclinar|incomodar|incorporar|incrementar|incrustar|independitzar|indignar|indisposar|inebriar|infatuar|infectar|infestar|infiltrar|inflamar|inflar|informar|ingerir|inhabilitar|inhibir|iniciar|inquietar|inscriure|insinuar|inspirar|instal·lar|instruir|insubordinar|insultar|insurreccionar|integrar|intensificar|interessar|interferir|internar|interposar|interrompre|intranquil·litzar|introduir|inundar|invaginar|inventar|ionitzar|irritar|islamitzar|isolar|jubilar|jugar|junyir|justificar|lamentar|laxar|lignificar|limitar|llampar|llançar|llassar|llatinitzar|llepar|lletrejar|llevar|llicenciar|lligar|lliurar|llogar|lluir|localitzar|lucrar|macerar|malacostumar|malavesar|maliciar|mallar|malpensar|mamar|mancomunar|manegar|manejar|manifestar|mantenir|maquillar|marcir|marejar|marginar|maridar|marinejar|mascarar|massificar|masturbar|materialitzar|matricular|matxucar|mecanitzar|mediumitzar|menar|menjar|mentalitzar|menysprear|meravellar|merèixer|mesclar|metal·litzar|metamorfosar|meteoritzar|migrar|millorar|mineralitzar|mirar|mobilitzar|mocar|moderar|modernitzar|modificar|molestar|morfondre|morir|morrejar|mortificar|mossegar|mostrar|moure|mudar|mullar|multiplicar|musteir|mustiar|mustigar|mutilar|nacionalitzar|naturalitzar|necrosar|negar|neguitejar|netejar|nonuplicar|normalitzar|nuar|oblidar|obligar|obnubilar|obscurir|occidentalitzar|occitanitzar|ocultar|ocupar|ofegar|oferir|ofuscar|ombrar|omplir|operar|oposar|ordenar|orejar|organitzar|orgullar|orientalitzar|orientar|originar|orinar|oscar|oxigenar|pacificar|paganitzar|pagar|pansir|parapetar|parar|parlar|particularitzar|partir|passar|passejar|pedregar|pedrejar|pellar|penjar|pensar|pentinar|percaçar|perfeccionar|perfilar|permetre|persignar|persuadir|pessigar|petar|picar|pintar|pirar|plantar|plantificar|podrir|polaritzar|polir|pol·linitzar|pondre|popularitzar|portar|posar|possessionar|posticar|postrar|prear|precipitar|prendre|preocupar|preparar|presentar|prestar|prevaler|privar|proclamar|prodigar|produir|professionalitzar|proletaritzar|prometre|pronunciar|propagar|propalar|proposar|prostituir|prostrar|prou|proveir|pujar|punxar|purificar|putejar|quadrar|qualificar|quallar|quedar|quitar|rabejar|radicalitzar|rarificar|ratificar|reafirmar|realitzar|rebaixar|rebentar|reblir|rebolcar|rebullir|recargolar|reciclar|reciprocar|recloure|recobrar|recollir|recolzar|reconcentrar|reconciliar|reconstituir|recordar|recrear|recriminar|rectificar|reencarnar|reenganxar|refer|referir|refermar|reflectir|refocil·lar|reforçar|refractar|refredar|refrenar|refrescar|refringir|refugiar|refusar|regalar|regelar|regirar|rehabilitar|rehidratar|reincorporar|reinflar|reinstal·lar|reintegrar|rejovenir|relacionar|relaxar|rellentir|relligar|rellogar|remenar|remetre|remirar|remollir|remudar|remuntar|rendir|renovar|renovellar|rentar|repatriar|repenjar|repensar|repetir|repintar|replegar|replujar|repodrir|reportar|reposar|representar|reprimir|reproduir|repuntar|rescabalar|reservar|resguardar|resignar|resinificar|resistir|resoldre|responsabilitzar|resquitar|ressecar|ressobinar|restablir|retardar|retenir|retintar|retirar|retractar|retre|retreure|retrobar|reunir|reveixinar|revelar|revellir|revenxinar|revestir|revifar|reviscolar|revoltar|rifar|rinxolar|riure|romanitzar|rombollar|rompre|rostir|rovellar|ruboritzar|russificar|sacrificar|salmorrar|salsir|salvar|santificar|satel·litzar|secularitzar|sedimentar|segar|segregar|seguir|sentir|senyar|separar|significar|silicificar|sincerar|sindicar|singularitzar|sinitzar|situar|sobrealimentar|sobreexcitar|sobreposar|sobresaltar|sobresanar|sobresaturar|sobtar|socarrar|solapar|solar|solaçar|soldar|solidaritzar|solidificar|sollar|sollevar|solvatar|somorgollar|soplujar|sostreure|sotaplujar|sotmetre|suberificar|suberitzar|subestimar|submergir|subscriure|suggestionar|sulfatar|sulfurar|sumar|sumir|superar|tallar|tancar|tant|tapar|temperar|tenyir|terraplenar|tirar|titular|tocar|tombar|torbar|torejar|tornar|torrar|trabucar|tractar|tranquil·litzar|transfigurar|transformar|translimitar|transmetre|transmutar|transparentar|transvasar|trasmudar|trasplantar|trastocar|trastornar|triar|tribular|trifurcar|trobar|tòrcer|ulcerar|ullar|unir|universalitzar|untar|vaporitzar|velar|venjar|ventar|vessar|vestir|viciar|vinclar|vincular|vitrificar|volar|volatilitzar|xalar|xutar");
  private static final Pattern NO_VERBS_POTENCIALMENT_PRONOMINALS = Pattern.compile("voler");
  private static final Pattern VERB_HAVER = Pattern.compile("haver");
  private static final Pattern VERB_ANAR = Pattern.compile("anar");
  private static final Pattern VERB_VENIR = Pattern.compile("venir");
  private static final Pattern NO_VERB = Pattern.compile("N.*|A.*|_GN_.*");
  private static final Pattern UPPERCASE = Pattern.compile("\\p{Lu}.*");
  // V[MAS][ISMNGP][PIFSC0][123][SP][MF]
  
  private static final Pattern VERB= Pattern.compile("V.*");
  private static final Pattern VERB_INDSUBJ = Pattern.compile("V.[SI].*");
  private static final Pattern VERB_INDSUBJIMP = Pattern.compile("V.[MSI].*");
  private static final Pattern VERB_IMP = Pattern.compile("V.M.*");
  private static final Pattern VERB_INF = Pattern.compile("V.N.*");
  private static final Pattern VERB_INFGER = Pattern.compile("V.[NG].*");
  private static final Pattern VERB_GERUNDI = Pattern.compile("V.G.*");
  private static final Pattern VERB_PARTICIPI = Pattern.compile("V.P.*");
  private static final Pattern VERB_AUXILIAR = Pattern.compile("VA.*");
  private static final Pattern PREP_VERB_PRONOM = Pattern.compile("_PUNCT_CONT|SPS00|V.*|P0.{6}|PP3CN000|PP3NN000|PP3..A00|PP3CP000|PP3CSD00");
  private static final Pattern PREP_VERB_PRONOM_ADV = Pattern.compile("RG.*|.*LOC_ADV.*|_PUNCT_CONT|SPS00|V.*|P0.{6}|PP3CN000|PP3NN000|PP3..A00|PP3CP000|PP3CSD00");
  //potser convé diferenciar la coma(,) de les cometes(") en _PUNCT_CONT -> no incloure la coma
  private static final Pattern VERB_PRONOM = Pattern.compile("V.*|P0.{6}|PP3CN000|PP3NN000|PP3..A00|PP3CP000|PP3CSD00");
  //cal restringir les preposicions  
  
  private static final Pattern VERB_1S = Pattern.compile("V...1S..?");
  private static final Pattern VERB_2S = Pattern.compile("V...2S..?");
  private static final Pattern VERB_3S = Pattern.compile("V...3S..?");
  private static final Pattern VERB_1P = Pattern.compile("V...1P..?");
  private static final Pattern VERB_2P = Pattern.compile("V...2P..?");
  private static final Pattern VERB_3P = Pattern.compile("V...3P..?");
  
  private static final Pattern PRONOM_FEBLE_1S = Pattern.compile("P010S000");
  private static final Pattern PRONOM_FEBLE_2S = Pattern.compile("P020S000");
  private static final Pattern PRONOM_FEBLE_3S = Pattern.compile("P0300000");
  private static final Pattern PRONOM_FEBLE_1P = Pattern.compile("P010P000");
  private static final Pattern PRONOM_FEBLE_2P = Pattern.compile("P020P000");
  private static final Pattern PRONOM_FEBLE_3P = Pattern.compile("P0300000");
  private static final Pattern PRONOM_FEBLE_13S = Pattern.compile("P010S000|P0300000");
  private static final Pattern PRONOM_FEBLE_23S = Pattern.compile("P020S000|P0300000");
  
  private static final Pattern PRONOM_FEBLE = Pattern.compile("P0.{6}|PP3CN000|PP3NN000|PP3..A00|PP3CP000|PP3CSD00"); // tots els pronoms febles
  private static final Pattern PRONOM_REFLEXIU = Pattern.compile("P0.0.*"); //me te se ens us (i variants)
  //private static final Pattern PRONOM_FEBLE_GUIONET = Pattern.compile("-.+");
  
  private static final Pattern LEMMA_EN = Pattern.compile("en");
  private static final Pattern POSTAG_EN = Pattern.compile("PP3CN000");
  private static final Pattern LEMMA_HI = Pattern.compile("hi");
  private static final Pattern POSTAG_HI = Pattern.compile("PP3CN000");
  private static final Pattern LEMMA_ES = Pattern.compile("es");
  private static final Pattern POSTAG_ES = Pattern.compile("P0300000");
  private static final Pattern LEMMA_PRONOM_CI = Pattern.compile("jo|tu|ell");
  private static final Pattern POSTAG_PRONOM_CI = Pattern.compile("P0.*|PP3CP000|PP3CSD00");
  private static final Pattern LEMMA_PRONOM_CD = Pattern.compile("jo|tu|ell");
  private static final Pattern POSTAG_PRONOM_CD = Pattern.compile("P0.*|PP3CP000|PP3..A00");
  private static final Pattern POSTAG_CD = Pattern.compile("_GN_.*|N.*|DI.*|P[DI].*");
  private static final Pattern LEMMA_DE = Pattern.compile("de");
  private static final Pattern POSTAG_DE = Pattern.compile("SPS00");
  private static final Pattern POSTAG_PREPOSICIO = Pattern.compile("SPS00");
  private static final Pattern LEMMA_PREP_A_PER = Pattern.compile("a|per");
  
  private static final Pattern POSTAG_ADVERBI = Pattern.compile("RG.*|.*LOC_ADV.*");
  private static final Pattern ANYMESDIA = Pattern.compile("any|mes|dia");
  
  private static final Pattern REFLEXIU_POSPOSAT = Pattern.compile("-[mts]|-[mts]e|'[mts]|-nos|'ns|-vos|-us",Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE);
  //private static final Pattern REFLEXIU_ANTEPOSAT = Pattern.compile("e[mts]|[mts]e|ens|us|-[mts]|-[mts]e|'[mts]|[mts]'|-nos|'ns|-vos|-us",Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE);
  private static final Pattern REFLEXIU_ANTEPOSAT = Pattern.compile("e[mts]|[mts]e|ens|us|'[mts]|[mts]'|'ns",Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE);
  
  private static final Pattern SUBJECTE_PERSONAL_POSTAG = Pattern.compile("NC.*|NP.*|_GN_.*|PI.*");
  private static final Pattern SUBJECTE_PERSONAL_NO_POSTAG = Pattern.compile("complement.*|D.*|A.*|PX.*");
  private static final Pattern SUBJECTE_PERSONAL_TOKEN = Pattern.compile("jo|mi|tu|ella?|nosaltres|vosaltres|elle?s|vost[èé]s?|vós",Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE);
  private static final Pattern SUBJECTE_PERSONAL_NO_LEMMA = Pattern.compile("dia|any|mes|segle|dilluns|dimarts|dimecres|dijous|divendres|dissabte|diumenge|gener|febrer|març|abril|maig|juny|juliol|agost|setembre|octubre|novembre|desembre");
  // en general expressió temporal
  
  private static final Pattern SUBJECTE_PERSONAL_SING_POSTAG = Pattern.compile("N..[SN].*|_GN_.S|PI..[SN].*");
  private static final Pattern SUBJECTE_PERSONAL_SING_TOKEN = Pattern.compile("jo|mi|tu|ella?|vost[èé]|vós",Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE);
  private static final Pattern SUBJECTE_PERSONAL_PL_POSTAG = Pattern.compile("N..[PN].*|_GN_.P|PI..[PN].*");
  private static final Pattern SUBJECTE_PERSONAL_PL_TOKEN = Pattern.compile("nosaltres|vosaltres|elle?s|vost[èé]s",Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE);
  
  private static final Pattern TRENCA_COMPTE = Pattern.compile("PR.*|CS|CC|_PUNCT.*|.*LOC_CONJ.*");
  private static final Pattern TRENCA_COMPTE2 = Pattern.compile("SENT_START|CC|_PUNCT.*|.*LOC_CONJ.*");
  
 // <token postag="P0.*|PP.*" postag_regexp="yes"><exception postag="_GN_.*" postag_regexp="yes"/><exception regexp="yes">jo|mi|tu|ella?|nosaltres|vosaltres|elle?s|vost[èé]s?|vós</exception><exception postag="allow_saxon_genitive">'s</exception></token>
  
   
  public ReflexiveVerbsRule(ResourceBundle messages) throws IOException {
	  if (messages != null) {
		  super.setCategory(new Category("Verbs"));
	  }
  }

  
  @Override
  public String getId() {
    return "REFLEXIVE_VERBS";
  }

  @Override
  public String getDescription() {
    return "Verbs reflexius: comproveu que porten el pronom adequat.";
  }

	@Override
	public RuleMatch[] match(final AnalyzedSentence text) {
		final List<RuleMatch> ruleMatches = new ArrayList<RuleMatch>();
		final AnalyzedTokenReadings[] tokens = text
				.getTokensWithoutWhitespace();
		loop: for (int i = 1; i < tokens.length; i++) { // ignoring token 0,
														// i.e., SENT_START

			final String token;
			if (i == 1) {
				token = tokens[i].getToken().toLowerCase();
			} else {
				token = tokens[i].getToken();
			}
			if (matchPostagRegexp(tokens[i], NO_VERB) 
					|| !matchPostagRegexp(tokens[i], VERB) )
				continue loop;
			final Matcher mUpperCase = UPPERCASE.matcher(tokens[i]
					.getToken());
			if (i > 1 && mUpperCase.matches())
				continue loop;
			
			// Comprova: portar-se/emportar-se
			if (i+2<tokens.length
					&& matchLemmaRegexp(tokens[i], VERBS_PORTAR_DUR)
					&& ! (matchPostagRegexp(tokens[i], VERB_INF) && isThereBefore(tokens,i,LEMMA_PREP_A_PER,POSTAG_PREPOSICIO))
					&& !hasVerbMultipleReadings(tokens[i]) //em duràs un mocador
					&& isThereReflexivePronoun(tokens, i) // ens portem, ens hem de portar
					&& isThereAfterWithoutPreposition(tokens, i, POSTAG_CD)
					&& !isThereVerbBefore(tokens,i,VERBS_DEIXAR_FER) // es deixen portar
					&& !(isThereVerbBefore(tokens,i,VERBS_POTENCIALMENT_PRONOMINALS)&&!isThereVerbBefore(tokens,i,NO_VERBS_POTENCIALMENT_PRONOMINALS))
					&& !matchPostagRegexp(tokens[i+1], POSTAG_ADVERBI) // es porten bé
					&& !matchPostagRegexp(tokens[i+2], POSTAG_ADVERBI) // hem de portar-nos bé
					&& !matchLemmaRegexp(tokens[i+2], ANYMESDIA) // ens portem tres anys
					&& !isPhraseImpersonalVerbSP(tokens, i) // Es va portar l'any passat
					) {
				// the rule matches
				String suggestion;
				if (matchLemmaRegexp(tokens[i], VERB_PORTAR)) {suggestion= "em"+token; }
					else if (token.equalsIgnoreCase("du")) {suggestion="endú"; }
					else {suggestion= "en"+token; }
				final String msg="¿Volíeu dir <suggestion>"+suggestion+"</suggestion>?";
				final RuleMatch ruleMatch = new RuleMatch(this,
						tokens[i].getStartPos(), tokens[i].getStartPos()
								+ token.length(), msg, "Possible error");
				ruleMatches.add(ruleMatch);		
				continue loop;
			}
			
			//PERÍFRASI AMB VERB PRONOMINAL: el fan *agenollar-se/agenollar
			if (i+1<tokens.length 
					&& matchPostagRegexp(tokens[i], VERB_INF)
					&& !matchPostagRegexp(tokens[i - 1], POSTAG_PREPOSICIO) 
					&& isThereVerbBefore(tokens,i,VERBS_DEIXAR_FER)
					&& isThereBefore(tokens, i, LEMMA_PRONOM_CD, POSTAG_PRONOM_CD)  
					&& matchRegexp(tokens[i + 1].getToken(), REFLEXIU_POSPOSAT) ) {
					// the rule matches
					final String msg = "En aquesta perífrasi verbal el pronom reflexiu posterior és redundant.";
					final RuleMatch ruleMatch = new RuleMatch(this,
							tokens[i+1].getStartPos(), tokens[i+1].getStartPos()
									+ tokens[i+1].getToken().length(), msg, "Pronom redundant");
					ruleMatches.add(ruleMatch);
					continue loop;
			}

			//VERBS PRONOMINALS: Cal que hi hagi pronom reflexiu. 
			if (matchLemmaRegexp(tokens[i], VERBS_PRONOMINALS)) {
				if (matchLemmaRegexp(tokens[i], NO_VERBS_PRONOMINALS)) 
					// atengué l'administració
					continue loop;
				if (matchPostagRegexp(tokens[i], VERB_PARTICIPI) && !matchLemmaRegexp(tokens[i - 1], VERB_HAVER)) 
					continue loop;
				if (isThereVerbBefore(tokens,i,VERBS_DEIXAR_FER)  // el fa agenollar
						&& isThereBefore(tokens, i, LEMMA_PRONOM_CD, POSTAG_PRONOM_CD) )
					continue loop;
				if (isThereReflexivePronoun(tokens, i)) 
					continue loop;
				// the rule matches
				final String msg = "Aquest verb és pronominal. Falta un pronom.";
				final RuleMatch ruleMatch = new RuleMatch(this,
						tokens[i].getStartPos(), tokens[i].getStartPos()
								+ token.length(), msg,
						"Verb pronominal: falta un pronom");
				ruleMatches.add(ruleMatch);
				continue loop;
			}
			
			//VERBS NO PRONOMINALS: No hi ha d'haver pronom reflexiu. 
			if (matchLemmaRegexp(tokens[i], VERBS_NO_PRONOMINALS)) {
				if (matchLemmaRegexp(tokens[i], NO_VERBS_NO_PRONOMINALS))
					continue loop;				
				if (!isThereReflexivePronoun(tokens, i)) 
					continue loop;
				//impersonal obligació: s'ha de baixar
				if (matchLemmaRegexp(tokens[i],VERBS_NO_PRONOMINALS_IMPERSONALS2)
						&& isThereBefore(tokens, i, LEMMA_ES, POSTAG_ES)
						&& isThereBefore(tokens, i, LEMMA_DE, POSTAG_DE)
						&& isThereVerbBefore(tokens,i,VERB_HAVER) )
						continue loop;
				if (isThereVerbBefore(tokens,i,VERBS_SOVINT_AMB_COMPLEMENT)
						|| (isThereVerbBefore(tokens,i,VERBS_POTENCIALMENT_PRONOMINALS)&&!isThereVerbBefore(tokens,i,NO_VERBS_POTENCIALMENT_PRONOMINALS))
						|| isThereVerbBefore(tokens,i,VERBS_PRONOMINALS)) //et deixes caure, et fas témer, 
					continue loop;
				//FRASE IMPERSONAL
				// És frase impersonal si hi ha el pronom 'es', llevat que es pugui identificar un subjecte "personal"
				if (matchLemmaRegexp(tokens[i],VERBS_NO_PRONOMINALS_IMPERSONALS)
						&& isPhraseImpersonalVerbS(tokens, i) )  
					continue loop;
				if (matchLemmaRegexp(tokens[i],VERBS_NO_PRONOMINALS_IMPERSONALS2)
						&& isPhraseImpersonalVerbSP(tokens, i) )  
					continue loop;
				
				// the rule matches
				final String msg = "Aquest verb no és pronominal. Sobra un pronom.";
				final RuleMatch ruleMatch = new RuleMatch(this,
						tokens[i].getStartPos(), tokens[i].getStartPos()
								+ token.length(), msg,
						"Verb no pronominal: sobra un pronom");
				ruleMatches.add(ruleMatch);
			}
			
			//VERBS DE MOVIMENT: si hi ha pronom reflexiu cal el pronom 'en'.
			if (matchLemmaRegexp(tokens[i], VERBS_MOVIMENT) && !matchPostagRegexp(tokens[i], VERB_AUXILIAR)) {
				if (matchLemmaRegexp(tokens[i], VERBS_NO_MOVIMENT)) 
					// atengué l'administració
					continue loop;
				//impersonal obligació: s'ha de baixar
				if (isThereBefore(tokens, i, LEMMA_ES, POSTAG_ES)
						&& isThereBefore(tokens, i, LEMMA_DE, POSTAG_DE)
						&& isThereVerbBefore(tokens,i,VERB_HAVER) )
						continue loop;
				if (isThereVerbBefore(tokens,i,VERBS_SOVINT_AMB_COMPLEMENT) 
						|| (isThereVerbBefore(tokens,i,VERBS_POTENCIALMENT_PRONOMINALS)&&!isThereVerbBefore(tokens,i,NO_VERBS_POTENCIALMENT_PRONOMINALS))
						|| isThereVerbBefore(tokens,i,VERBS_PRONOMINALS) //et deixes anar/pujar
						|| isThereVerbAfter(tokens,i,VERBS_SOVINT_AMB_COMPLEMENT) ) // per venir-vos a veure 
					continue loop;
				if (matchLemmaRegexp(tokens[i], VERB_VENIR)) {
					if (isThereAfter(tokens, i, VERB_INF))
						continue loop;
				}
				if (matchLemmaRegexp(tokens[i], VERB_ANAR)) {
					if (isThereAfter(tokens, i, VERB_GERUNDI))
						continue loop;
					if (isThereVerbAfter(tokens, i,
							VERBS_POTENCIALMENT_PRONOMINALS)
							||isThereVerbAfter(tokens,i,VERBS_PRONOMINALS))
						continue loop;
					if (hasVerbMultipleReadings(tokens[i]) && isThereAfter(tokens,i,POSTAG_ADVERBI))
						continue loop;
					//FRASE IMPERSONAL
					if (isThereBefore(tokens, i, LEMMA_ES, POSTAG_ES)
							&& !isThereBefore(tokens, i, LEMMA_PRONOM_CI, POSTAG_PRONOM_CI)
							&& (!isTherePersonalSubjectBefore(tokens,i,TRENCA_COMPTE) || isThereBefore(tokens, i, LEMMA_HI, POSTAG_HI)) 
							&& isVerbNumberPerson(tokens,i,VERB_3S))
						continue loop;
				}
				else {
					// FRASE IMPERSONAL
					if (isThereBefore(tokens, i, LEMMA_ES, POSTAG_ES)
							&& !isThereBefore(tokens, i, LEMMA_PRONOM_CI, POSTAG_PRONOM_CI)
							&& !isTherePersonalSubjectBefore(tokens, i,	TRENCA_COMPTE))
						continue loop;
				}
				if (isThereReflexivePronoun(tokens, i) && (!isTherePronoun(tokens, i, LEMMA_EN, POSTAG_EN))) {
					// the rule matches
					final String msg = "No useu com a pronominal aquest verb, o bé afegiu-hi el pronom 'en'."; //Cal canviar el missatge
					final RuleMatch ruleMatch = new RuleMatch(this,
							tokens[i].getStartPos(), tokens[i].getStartPos()
									+ token.length(), msg,
							"Falta el pronom 'en'");
					ruleMatches.add(ruleMatch);
				}
			}
		}
		return toRuleMatchArray(ruleMatches);
	}

  /**
   * Find appropiate pronoun pattern. (Troba el pronom feble apropiat)
   */ 
  private Pattern pronomPattern(AnalyzedTokenReadings aToken) {
	if (matchPostagRegexp(aToken,VERB_1S) && matchPostagRegexp(aToken,VERB_3S))
		return PRONOM_FEBLE_13S;
	if (matchPostagRegexp(aToken,VERB_2S) && matchPostagRegexp(aToken,VERB_3S))
		return PRONOM_FEBLE_23S;
	else if (matchPostagRegexp(aToken,VERB_1S) )
		return PRONOM_FEBLE_1S;
	else if (matchPostagRegexp(aToken,VERB_2S) )
		return PRONOM_FEBLE_2S;
	else if (matchPostagRegexp(aToken,VERB_3S) )
		return PRONOM_FEBLE_3S;
	else if (matchPostagRegexp(aToken,VERB_1P) )
		return PRONOM_FEBLE_1P;
	else if (matchPostagRegexp(aToken,VERB_2P) )
		return PRONOM_FEBLE_2P;
	else if (matchPostagRegexp(aToken,VERB_3P) )
		return PRONOM_FEBLE_3P;
	else
		return null;
  }
  
  /**
   * El verb té múltiples lectures
   */ 
  private boolean hasVerbMultipleReadings (AnalyzedTokenReadings aToken) {
	return (matchPostagRegexp(aToken,VERB_1S) && matchPostagRegexp(aToken,VERB_3S))
			|| (matchPostagRegexp(aToken,VERB_2S) && matchPostagRegexp(aToken,VERB_3S));
  }
  
  /**
   * Match POS tag with regular expression
   */
  private boolean matchPostagRegexp(AnalyzedTokenReadings aToken, Pattern pattern) {
    boolean matches = false;
    final int readingsLen = aToken.getReadingsLength();
    for (int i = 0; i < readingsLen; i++) {
      final String posTag = aToken.getAnalyzedToken(i).getPOSTag();
      if (posTag != null) {
        final Matcher m = pattern.matcher(posTag);
        if (m.matches()) {
          matches = true;
          break;
        }
      }
    }
    return matches;
  }
  
	/**
	 * Match lemma with regular expression
	 */
	private boolean matchLemmaRegexp(AnalyzedTokenReadings aToken,
			Pattern pattern) {
		boolean matches = false;
		final int readingsLen = aToken.getReadingsLength();
		for (int i = 0; i < readingsLen; i++) {
			final String posTag = aToken.getAnalyzedToken(i).getLemma();
			if (posTag != null) {
				final Matcher m = pattern.matcher(posTag);
				if (m.matches()) {
					matches = true;
					break;
				}
			}
		}
		return matches;
	}
	
	/**
	 * Match String with regular expression
	 */
	private boolean matchRegexp(String s, Pattern pattern) {
		final Matcher m = pattern.matcher(s);
		return m.matches();
	}
  
	/**
	 * Checks if there is a reflexive pronoun near the verb
	 * 
	 * @param tokens
	 * @param i
	 * @return
	 */
	private boolean isThereReflexivePronoun(
			final AnalyzedTokenReadings[] tokens, int i) {
		Pattern pPronomBuscat = null;
		// 1) es queixa, se li queixa, se li'n queixa
		if (matchPostagRegexp(tokens[i], VERB_INDSUBJ)) {
			pPronomBuscat = pronomPattern(tokens[i]);
			if (pPronomBuscat != null) {
				int j = 1;
				boolean keepCounting = true;
				while (i - j > 0 && j < 4 && keepCounting) {
					if (matchPostagRegexp(tokens[i - j], pPronomBuscat)
							&& matchRegexp(tokens[i -j].getToken(), REFLEXIU_ANTEPOSAT))
						return true;
					keepCounting = matchPostagRegexp(tokens[i - j],
							PRONOM_FEBLE);
					j++;
				}
			}
		}
		// 2) queixa't, queixeu-vos-hi
		if (matchPostagRegexp(tokens[i], VERB_IMP)) {
			pPronomBuscat = pronomPattern(tokens[i]);
			if (pPronomBuscat != null) {
				if (i+1<tokens.length
						&& matchPostagRegexp(tokens[i + 1], pPronomBuscat)
						&& matchRegexp(tokens[i + 1].getToken(), REFLEXIU_POSPOSAT))
					return true; 
			}
		}
		// 3) s'ha queixat, se li ha queixat, se li n'ha queixat.
		if (matchPostagRegexp(tokens[i], VERB_PARTICIPI)) {
			if (matchLemmaRegexp(tokens[i - 1], VERB_HAVER)
					&& matchPostagRegexp(tokens[i - 1], VERB_INDSUBJ)) {
				pPronomBuscat = pronomPattern(tokens[i - 1]);
				if (pPronomBuscat != null) {
					int j = 2;
					boolean keepCounting = true;
					while (i - j > 0 && j < 5 && keepCounting) {
						if (matchPostagRegexp(tokens[i - j], pPronomBuscat)
								&& matchRegexp(tokens[i -j].getToken(), REFLEXIU_ANTEPOSAT))
							return true;
						keepCounting = matchPostagRegexp(tokens[i - j],
								PRONOM_FEBLE);
						j++;
					}
				}
			}
			// *havent queixat, *haver queixat
//			else if (!(matchLemmaRegexp(tokens[i - 1], VERB_HAVER) && matchPostagRegexp(
//					tokens[i - 1], VERB_INFGER)))
//				return true;
		}
		// 4) em vaig queixar, se li va queixar, se li'n va queixar, vas
		// queixar-te'n,
		// em puc queixar, ens en podem queixar, podeu queixar-vos,
		// es va queixant, va queixant-se, comences queixant-te
		// 5) no t'has de queixar, no has de queixar-te, pareu de queixar-vos,
		// comenceu a queixar-vos
		// corre a queixar-se, corre a queixar-te, vés a queixar-te
		// no hauria pogut burlar-me
		// 6) no podeu deixar de queixar-vos, no us podeu deixar de queixar
		// en teniu prou amb queixar-vos, comenceu lentament a queixar-vos
		// 7) no es va poder emportar, va decidir suïcidar-se,
		// 8) Queixar-se, queixant-vos, podent abstenir-se
		if (matchPostagRegexp(tokens[i], VERB_INFGER)) {
			int k = 1;
			boolean keepCounting = true;
			boolean foundVerb = false;
			while (i - k > 0 && keepCounting && !foundVerb) {
				foundVerb = matchPostagRegexp(tokens[i - k], VERB_INDSUBJIMP);
				keepCounting = matchPostagRegexp(tokens[i - k],
						PREP_VERB_PRONOM);
				if (matchPostagRegexp(tokens[i-k],VERB_INDSUBJ)
						&& matchPostagRegexp(tokens[i-k+1],VERB_INFGER))
					keepCounting=false;
				k++;
			}
			if (foundVerb) {
				pPronomBuscat = pronomPattern(tokens[i - k + 1]);
				if (pPronomBuscat != null) {
					if (i+1< tokens.length
							&& matchPostagRegexp(tokens[i + 1], pPronomBuscat)
							&& matchRegexp(tokens[i + 1].getToken(), REFLEXIU_POSPOSAT))
						return true;
					int j = 1;
					keepCounting = true;
					while (i - j > 0 && keepCounting) {
						if (j==1 && matchPostagRegexp(tokens[i - j], pPronomBuscat))
							return true;
						if (j>1 && matchPostagRegexp(tokens[i - j], pPronomBuscat)
								&& matchRegexp(tokens[i - j].getToken(), REFLEXIU_ANTEPOSAT))
							return true;
						keepCounting = matchPostagRegexp(tokens[i - j], PREP_VERB_PRONOM)
								&& !(j>k-1 && matchPostagRegexp(tokens[i - j], VERB_PARTICIPI))
								&& !matchPostagRegexp(tokens[i - j], TRENCA_COMPTE2);
						if (tokens[i-j].getToken().equalsIgnoreCase("per")
								&& tokens[i-j+1].getToken().equalsIgnoreCase("a"))
							keepCounting=false;
						j++;
					}
				}
			} else {
				if (i+1<tokens.length
						&& matchPostagRegexp(tokens[i + 1], PRONOM_REFLEXIU)
						&& matchRegexp(tokens[i + 1].getToken(), REFLEXIU_POSPOSAT))
					return true;
				int j = 1;
				keepCounting = true;
				while (i - j > 0 && keepCounting) {
					if (matchPostagRegexp(tokens[i - j], PRONOM_REFLEXIU))
						return true;
					keepCounting = matchPostagRegexp(tokens[i - j],
							PREP_VERB_PRONOM);
					if (tokens[i-j].getToken().equalsIgnoreCase("per")
							&& tokens[i-j+1].getToken().equalsIgnoreCase("a"))
						keepCounting=false;
					j++;
				}
			}
		}
		return false;
	}
	
	/**
	 * Checks if there is a desired pronoun near the verb
	 * 
	 * @param tokens
	 * @param i
	 * @return
	 */
	private boolean isTherePronoun(final AnalyzedTokenReadings[] tokens, int i,
			Pattern lemma, Pattern postag) {
		int j = 1;
		boolean keepCounting = true;
		while (i - j > 0 && keepCounting) {
			if (matchPostagRegexp(tokens[i - j], postag)
					&& matchLemmaRegexp(tokens[i - j], lemma))
				return true;
			keepCounting = matchPostagRegexp(tokens[i - j], PREP_VERB_PRONOM);
			j++;
		}
		j = 1;
		keepCounting = true;
		while (i + j < tokens.length && keepCounting) {
			if (matchPostagRegexp(tokens[i + j], postag)
					&& matchLemmaRegexp(tokens[i + j], lemma))
				return true;
			keepCounting = matchPostagRegexp(tokens[i + j], PREP_VERB_PRONOM);
			j++;
		}
		return false;
	}

	private boolean isThereBefore(final AnalyzedTokenReadings[] tokens,
			int i, Pattern lemma, Pattern postag) {
		int j = 1;
		boolean keepCounting = true;
		while (i - j > 0 && keepCounting) {
			if (matchPostagRegexp(tokens[i - j], postag)
					&& matchLemmaRegexp(tokens[i - j], lemma))
				return true;
			keepCounting = matchPostagRegexp(tokens[i - j], PREP_VERB_PRONOM);
			j++;
		}
		return false;
	}

	private boolean isThereAfter(final AnalyzedTokenReadings[] tokens, int i, Pattern postag) {
		int j = 1;
		boolean keepCounting = true;
		while (i+j<tokens.length && keepCounting) {
			if (matchPostagRegexp(tokens[i+j], postag))
				return true;
			keepCounting = matchPostagRegexp(tokens[i+j],
					PREP_VERB_PRONOM_ADV);
			j++;
		}
		return false;
	}
	
	private boolean isThereAfterWithoutPreposition(final AnalyzedTokenReadings[] tokens, int i, Pattern postag) {
		int j = 1;
		boolean keepCounting = true;
		while (i+j<tokens.length && keepCounting) {
			if (matchPostagRegexp(tokens[i+j], postag))
				return true;
			keepCounting = matchPostagRegexp(tokens[i+j],
					VERB_PRONOM);
			j++;
		}
		return false;
	}
	
	private boolean isThereVerbBefore(final AnalyzedTokenReadings[] tokens, int i, Pattern lemma) {
		int j = 1;
		boolean keepCounting = true;
		while (i-j>0 && keepCounting) {
			if (matchLemmaRegexp(tokens[i-j], lemma))
				return true;
			keepCounting = matchPostagRegexp(tokens[i - j],
					PREP_VERB_PRONOM);
			if (tokens[i-j].getToken().equalsIgnoreCase("per")
					&& tokens[i-j+1].getToken().equalsIgnoreCase("a"))
				keepCounting=false;
			if (matchPostagRegexp(tokens[i-j],VERB_INDSUBJ)
					&& matchPostagRegexp(tokens[i-j+1],VERB_INFGER))
				keepCounting=false;
			j++;
		}
		return false;
	}
	
	private boolean isThereVerbAfter(final AnalyzedTokenReadings[] tokens, int i, Pattern lemma) {
		int j = 1;
		boolean keepCounting = true;
		while (i+j<tokens.length && keepCounting) {
			if (matchLemmaRegexp(tokens[i+j], lemma))
				return true;
			keepCounting = matchPostagRegexp(tokens[i+j],
					PREP_VERB_PRONOM);
			j++;
		}
		return false;
	}
	
	private boolean isTherePersonalSubjectBefore(final AnalyzedTokenReadings[] tokens, int i,
			Pattern pTrenca) {
		int j = 1;
		boolean keepCounting = true;
		while (i - j > 0 && keepCounting) {
			if (matchRegexp(tokens[i - j].getToken(), SUBJECTE_PERSONAL_TOKEN)
					|| matchPostagRegexp(tokens[i - j], SUBJECTE_PERSONAL_POSTAG)
					&& !matchPostagRegexp(tokens[i - j], SUBJECTE_PERSONAL_NO_POSTAG)
					&& !matchLemmaRegexp(tokens[i-j], SUBJECTE_PERSONAL_NO_LEMMA))
				return true;
			keepCounting = !matchPostagRegexp(tokens[i - j], pTrenca);
			j++;
		}
		return false;
	}
	
	private boolean isThereSingularPersonalSubjectBefore(final AnalyzedTokenReadings[] tokens, int i,
			Pattern pTrenca) {
		int j = 1;
		boolean keepCounting = true;
		while (i - j > 0 && keepCounting) {
			if (matchRegexp(tokens[i - j].getToken(), SUBJECTE_PERSONAL_SING_TOKEN)
					|| matchPostagRegexp(tokens[i - j], SUBJECTE_PERSONAL_SING_POSTAG)
					&& !matchPostagRegexp(tokens[i - j], SUBJECTE_PERSONAL_NO_POSTAG)
					&& !matchLemmaRegexp(tokens[i-j], SUBJECTE_PERSONAL_NO_LEMMA))
				return true;
			keepCounting = !matchPostagRegexp(tokens[i - j], pTrenca);
			j++;
		}
		return false;
	}
	
	private boolean isTherePluralPersonalSubjectBefore(final AnalyzedTokenReadings[] tokens, int i,
			Pattern pTrenca) {
		int j = 1;
		boolean keepCounting = true;
		while (i - j > 0 && keepCounting) {
			if (matchRegexp(tokens[i - j].getToken(), SUBJECTE_PERSONAL_PL_TOKEN)
					|| matchPostagRegexp(tokens[i - j], SUBJECTE_PERSONAL_PL_POSTAG)
					&& !matchPostagRegexp(tokens[i - j], SUBJECTE_PERSONAL_NO_POSTAG)
					&& !matchLemmaRegexp(tokens[i-j], SUBJECTE_PERSONAL_NO_LEMMA))
				return true;
			keepCounting = !matchPostagRegexp(tokens[i - j], pTrenca);
			j++;
		}
		return false;
	}
	
	private boolean isVerbNumberPerson(final AnalyzedTokenReadings[] tokens, int i, Pattern pVerb){
		int j = 0; // El verb principal pot ser conjugat
		boolean keepCounting = true;
		while (i-j>0 && keepCounting) {
			if (matchPostagRegexp(tokens[i-j], pVerb))
				return true;
			keepCounting = matchPostagRegexp(tokens[i - j],
					PREP_VERB_PRONOM);
			if (tokens[i-j].getToken().equalsIgnoreCase("per")
					&& tokens[i-j+1].getToken().equalsIgnoreCase("a"))
				keepCounting=false;
			j++;
		}
		return false;
	}
	
	
	private boolean isPhraseImpersonalVerbS (final AnalyzedTokenReadings[] tokens, int i) {
		//FRASE IMPERSONAL
		// És frase impersonal si hi ha el pronom 'es', llevat que es pugui identificar un subjecte "personal".
		return isThereBefore(tokens, i, LEMMA_ES, POSTAG_ES)
		&& !isThereBefore(tokens, i, LEMMA_PRONOM_CI, POSTAG_PRONOM_CI)
		&& ( !isThereSingularPersonalSubjectBefore(tokens,i,TRENCA_COMPTE2) || isThereBefore(tokens, i, LEMMA_HI, POSTAG_HI))
		&& isVerbNumberPerson(tokens,i,VERB_3S);  		
	}
	private boolean isPhraseImpersonalVerbSP (final AnalyzedTokenReadings[] tokens, int i) {
		//FRASE IMPERSONAL
		// És frase impersonal si hi ha el pronom 'es', llevat que es pugui identificar un subjecte "personal".
		return isThereBefore(tokens, i, LEMMA_ES, POSTAG_ES)		
		&& !isThereBefore(tokens, i, LEMMA_PRONOM_CI, POSTAG_PRONOM_CI)
		&& (  (  (isVerbNumberPerson(tokens,i,VERB_3S) && !isThereSingularPersonalSubjectBefore(tokens,i,TRENCA_COMPTE))
		      || (isVerbNumberPerson(tokens,i,VERB_3P) && !isTherePluralPersonalSubjectBefore(tokens,i,TRENCA_COMPTE)) )
		   || isThereBefore(tokens, i, LEMMA_HI, POSTAG_HI));  		
	}
	
	@Override
	public void reset() {
		// nothing
	}
}