/**
 * Copyright 2015-2016 the original author or authors.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.neo4art.api.mobile.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Gianmarco Laggia
 * @since 4 May 2016
 */
@Controller
@RequestMapping("/api/mobile")
public class MobileAppRestController {

	@RequestMapping(value = "/pois/around/{lat}/{lon}/radius/{radius}", method = RequestMethod.GET, produces = "application/json") public @ResponseBody String getInterestingPointAround(
			@PathVariable(value = "lat") double lat, @PathVariable(value = "lon") double lon, @PathVariable(value = "radius") int radius) {

		//return "Asked for points around lat:" + lat + " lon:" + lon +" In a radius of "+radius + "km";
		return "[\n"
				+ "{\"title\": \"punto1\" , \"type\": \"type1\" , \"image\": \"image1\" , \"description\": \"punto1\" , \"lat\": 45.537459443068286 , \"lng\": 12.055196414463548},\n"
				+ "{\"title\": \"punto2\" , \"type\": \"type1\" , \"image\": \"image2\" , \"description\": \"punto2\" , \"lat\": 45.56429195149515 , \"lng\": 12.060016168521194},\n"
				+ "{\"title\": \"punto3\" , \"type\": \"type1\" , \"image\": \"image3\" , \"description\": \"punto3\" , \"lat\": 45.53608796472526 , \"lng\": 11.980239972533832},\n"
				+ "{\"title\": \"punto4\" , \"type\": \"type1\" , \"image\": \"image4\" , \"description\": \"punto4\" , \"lat\": 45.505674861751125 , \"lng\": 11.97860859357736},\n"
				+ "{\"title\": \"punto5\" , \"type\": \"type1\" , \"image\": \"image5\" , \"description\": \"punto5\" , \"lat\": 45.52240742435226 , \"lng\": 12.124362343605725},\n"
				+ "{\"title\": \"punto6\" , \"type\": \"type1\" , \"image\": \"image6\" , \"description\": \"punto6\" , \"lat\": 45.473636530599684 , \"lng\": 12.06833443618333},\n"
				+ "{\"title\": \"punto7\" , \"type\": \"type2\" , \"image\": \"image7\" , \"description\": \"punto7\" , \"lat\": 45.52356290802666 , \"lng\": 12.084727651462316},\n"
				+ "{\"title\": \"punto8\" , \"type\": \"type2\" , \"image\": \"image8\" , \"description\": \"punto8\" , \"lat\": 45.519993727831796 , \"lng\": 12.006357164544866},\n"
				+ "{\"title\": \"punto9\" , \"type\": \"type2\" , \"image\": \"image9\" , \"description\": \"punto9\" , \"lat\": 45.5686829650633 , \"lng\": 12.064387104256966},\n"
				+ "{\"title\": \"punto10\" , \"type\": \"type3\" , \"image\": \"image10\" , \"description\": \"punto10\" , \"lat\": 45.5145288871103 , \"lng\": 12.023670709528817},\n"
				+ "{\"title\": \"punto11\" , \"type\": \"type3\" , \"image\": \"image11\" , \"description\": \"punto11\" , \"lat\": 45.5824486740658 , \"lng\": 12.061123085618398},\n"
				+ "{\"title\": \"punto12\" , \"type\": \"type3\" , \"image\": \"image12\" , \"description\": \"punto12\" , \"lat\": 45.511928170815786 , \"lng\": 11.926198968693662},\n"
				+ "{\"title\": \"punto13\" , \"type\": \"type4\" , \"image\": \"image13\" , \"description\": \"punto13\" , \"lat\": 45.52245132570767 , \"lng\": 11.988766259923802},\n"
				+ "{\"title\": \"punto14\" , \"type\": \"type4\" , \"image\": \"image14\" , \"description\": \"punto14\" , \"lat\": 45.51856956786228 , \"lng\": 11.950286392451279},\n"
				+ "{\"title\": \"punto15\" , \"type\": \"type4\" , \"image\": \"image15\" , \"description\": \"punto15\" , \"lat\": 45.513560423209945 , \"lng\": 12.127017431768268},\n"
				+ "{\"title\": \"punto16\" , \"type\": \"type5\" , \"image\": \"image16\" , \"description\": \"punto16\" , \"lat\": 45.495190340052 , \"lng\": 12.078669864842649},\n"
				+ "{\"title\": \"punto17\" , \"type\": \"type5\" , \"image\": \"image17\" , \"description\": \"punto17\" , \"lat\": 45.44759688065153 , \"lng\": 12.052324481493809},\n"
				+ "{\"title\": \"punto18\" , \"type\": \"type5\" , \"image\": \"image18\" , \"description\": \"punto18\" , \"lat\": 45.56176762355905 , \"lng\": 12.00841340803637},\n"
				+ "{\"title\": \"punto19\" , \"type\": \"type6\" , \"image\": \"image19\" , \"description\": \"punto19\" , \"lat\": 45.51538584156791 , \"lng\": 12.036972625579619},\n"
				+ "{\"title\": \"punto20\" , \"type\": \"type6\" , \"image\": \"image20\" , \"description\": \"punto20\" , \"lat\": 45.579280752259386 , \"lng\": 12.046445717912642},\n"
				+ "{\"title\": \"punto21\" , \"type\": \"type6\" , \"image\": \"image21\" , \"description\": \"punto21\" , \"lat\": 45.52118696667185 , \"lng\": 12.03389519433329},\n"
				+ "{\"title\": \"punto22\" , \"type\": \"type7\" , \"image\": \"image22\" , \"description\": \"punto22\" , \"lat\": 45.57972942411168 , \"lng\": 11.977117155165066},\n"
				+ "{\"title\": \"punto23\" , \"type\": \"type7\" , \"image\": \"image23\" , \"description\": \"punto23\" , \"lat\": 45.512247772683175 , \"lng\": 12.100448899984114},\n"
				+ "{\"title\": \"punto24\" , \"type\": \"type7\" , \"image\": \"image24\" , \"description\": \"punto24\" , \"lat\": 45.53508525776769 , \"lng\": 12.086584700305348},\n"
				+ "{\"title\": \"punto25\" , \"type\": \"type8\" , \"image\": \"image25\" , \"description\": \"punto25\" , \"lat\": 45.57315826923384 , \"lng\": 12.04382593006755},\n"
				+ "{\"title\": \"punto26\" , \"type\": \"type8\" , \"image\": \"image26\" , \"description\": \"punto26\" , \"lat\": 45.56762230831659 , \"lng\": 12.03673182698558},\n"
				+ "{\"title\": \"punto27\" , \"type\": \"type8\" , \"image\": \"image27\" , \"description\": \"punto27\" , \"lat\": 45.52580099912549 , \"lng\": 12.075099489877633},\n"
				+ "{\"title\": \"punto28\" , \"type\": \"type9\" , \"image\": \"image28\" , \"description\": \"punto28\" , \"lat\": 45.59835237907679 , \"lng\": 12.02110765433677},\n"
				+ "{\"title\": \"punto29\" , \"type\": \"type9\" , \"image\": \"image29\" , \"description\": \"punto29\" , \"lat\": 45.473444242662985 , \"lng\": 12.003940353525115},\n"
				+ "{\"title\": \"punto30\" , \"type\": \"type9\" , \"image\": \"image30\" , \"description\": \"punto30\" , \"lat\": 45.5224434234637 , \"lng\": 11.949388755807533},\n"
				+ "{\"title\": \"punto31\" , \"type\": \"type10\" , \"image\": \"image31\" , \"description\": \"punto31\" , \"lat\": 45.52210626105414 , \"lng\": 11.974488542240612},\n"
				+ "{\"title\": \"punto32\" , \"type\": \"type10\" , \"image\": \"image32\" , \"description\": \"punto32\" , \"lat\": 45.503406917730615 , \"lng\": 12.130500816665279},\n"
				+ "{\"title\": \"punto33\" , \"type\": \"type10\" , \"image\": \"image33\" , \"description\": \"punto33\" , \"lat\": 45.53879053216433 , \"lng\": 12.10363853581091},\n"
				+ "{\"title\": \"punto34\" , \"type\": \"type11\" , \"image\": \"image34\" , \"description\": \"punto34\" , \"lat\": 45.531380861398155 , \"lng\": 12.092337391324191},\n"
				+ "{\"title\": \"punto35\" , \"type\": \"type11\" , \"image\": \"image35\" , \"description\": \"punto35\" , \"lat\": 45.48028582989015 , \"lng\": 11.992148786770954},\n"
				+ "{\"title\": \"punto36\" , \"type\": \"type11\" , \"image\": \"image36\" , \"description\": \"punto36\" , \"lat\": 45.51552632590523 , \"lng\": 12.05043717523582},\n"
				+ "{\"title\": \"punto37\" , \"type\": \"type12\" , \"image\": \"image37\" , \"description\": \"punto37\" , \"lat\": 45.52616011221274 , \"lng\": 12.120672199706503},\n"
				+ "{\"title\": \"punto38\" , \"type\": \"type12\" , \"image\": \"image38\" , \"description\": \"punto38\" , \"lat\": 45.55267916496198 , \"lng\": 12.032908046170293},\n"
				+ "{\"title\": \"punto39\" , \"type\": \"type12\" , \"image\": \"image39\" , \"description\": \"punto39\" , \"lat\": 45.440446227882276 , \"lng\": 12.025832853972673},\n"
				+ "{\"title\": \"punto40\" , \"type\": \"type13\" , \"image\": \"image40\" , \"description\": \"punto40\" , \"lat\": 45.54841283124319 , \"lng\": 12.129459457300484},\n"
				+ "{\"title\": \"punto41\" , \"type\": \"type13\" , \"image\": \"image41\" , \"description\": \"punto41\" , \"lat\": 45.55299613274804 , \"lng\": 12.085417268378228},\n"
				+ "{\"title\": \"punto42\" , \"type\": \"type13\" , \"image\": \"image42\" , \"description\": \"punto42\" , \"lat\": 45.56699100682579 , \"lng\": 12.064905262488118},\n"
				+ "{\"title\": \"punto43\" , \"type\": \"type14\" , \"image\": \"image43\" , \"description\": \"punto43\" , \"lat\": 45.45636924948965 , \"lng\": 12.027645777418892},\n"
				+ "{\"title\": \"punto44\" , \"type\": \"type14\" , \"image\": \"image44\" , \"description\": \"punto44\" , \"lat\": 45.58404756142985 , \"lng\": 12.039742439773876},\n"
				+ "{\"title\": \"punto45\" , \"type\": \"type14\" , \"image\": \"image45\" , \"description\": \"punto45\" , \"lat\": 45.512057240800694 , \"lng\": 12.04015217560143},\n"
				+ "{\"title\": \"punto46\" , \"type\": \"type15\" , \"image\": \"image46\" , \"description\": \"punto46\" , \"lat\": 45.54558382790054 , \"lng\": 12.066634978043203},\n"
				+ "{\"title\": \"punto47\" , \"type\": \"type15\" , \"image\": \"image47\" , \"description\": \"punto47\" , \"lat\": 45.55312871484138 , \"lng\": 12.083906919081587},\n"
				+ "{\"title\": \"punto48\" , \"type\": \"type15\" , \"image\": \"image48\" , \"description\": \"punto48\" , \"lat\": 45.51243742653855 , \"lng\": 12.066771136410512},\n"
				+ "{\"title\": \"punto49\" , \"type\": \"type16\" , \"image\": \"image49\" , \"description\": \"punto49\" , \"lat\": 45.46268314242478 , \"lng\": 12.084345651598474},\n"
				+ "{\"title\": \"punto50\" , \"type\": \"type16\" , \"image\": \"image50\" , \"description\": \"punto50\" , \"lat\": 45.45787330992601 , \"lng\": 11.969235098568571},\n"
				+ "{\"title\": \"punto51\" , \"type\": \"type16\" , \"image\": \"image51\" , \"description\": \"punto51\" , \"lat\": 45.566641552036714 , \"lng\": 12.070409590559182},\n"
				+ "{\"title\": \"punto52\" , \"type\": \"type17\" , \"image\": \"image52\" , \"description\": \"punto52\" , \"lat\": 45.50049274575847 , \"lng\": 12.036884374785991},\n"
				+ "{\"title\": \"punto53\" , \"type\": \"type17\" , \"image\": \"image53\" , \"description\": \"punto53\" , \"lat\": 45.60398141086752 , \"lng\": 12.045546820543272},\n"
				+ "{\"title\": \"punto54\" , \"type\": \"type17\" , \"image\": \"image54\" , \"description\": \"punto54\" , \"lat\": 45.52208782248487 , \"lng\": 11.93076657762666},\n"
				+ "{\"title\": \"punto55\" , \"type\": \"type18\" , \"image\": \"image55\" , \"description\": \"punto55\" , \"lat\": 45.49517102345562 , \"lng\": 12.066865690832255},\n"
				+ "{\"title\": \"punto56\" , \"type\": \"type18\" , \"image\": \"image56\" , \"description\": \"punto56\" , \"lat\": 45.47540926733116 , \"lng\": 12.029899954833244},\n"
				+ "{\"title\": \"punto57\" , \"type\": \"type18\" , \"image\": \"image57\" , \"description\": \"punto57\" , \"lat\": 45.51239528123735 , \"lng\": 12.121242047688208},\n"
				+ "{\"title\": \"punto58\" , \"type\": \"type19\" , \"image\": \"image58\" , \"description\": \"punto58\" , \"lat\": 45.56460189506435 , \"lng\": 12.046387724533973},\n"
				+ "{\"title\": \"punto59\" , \"type\": \"type19\" , \"image\": \"image59\" , \"description\": \"punto59\" , \"lat\": 45.523006238840054 , \"lng\": 12.11495102682824},\n"
				+ "{\"title\": \"punto60\" , \"type\": \"type19\" , \"image\": \"image60\" , \"description\": \"punto60\" , \"lat\": 45.52269629527086 , \"lng\": 12.079304009831139},\n"
				+ "{\"title\": \"punto61\" , \"type\": \"type20\" , \"image\": \"image61\" , \"description\": \"punto61\" , \"lat\": 45.53574026599041 , \"lng\": 12.009021077786771},\n"
				+ "{\"title\": \"punto62\" , \"type\": \"type20\" , \"image\": \"image62\" , \"description\": \"punto62\" , \"lat\": 45.538428784995745 , \"lng\": 11.954527473447857},\n"
				+ "{\"title\": \"punto63\" , \"type\": \"type20\" , \"image\": \"image63\" , \"description\": \"punto63\" , \"lat\": 45.526949458583026 , \"lng\": 12.052388778500594},\n"
				+ "{\"title\": \"punto64\" , \"type\": \"type21\" , \"image\": \"image64\" , \"description\": \"punto64\" , \"lat\": 45.52960022242271 , \"lng\": 12.032571432428888},\n"
				+ "{\"title\": \"punto65\" , \"type\": \"type21\" , \"image\": \"image65\" , \"description\": \"punto65\" , \"lat\": 45.52353129905077 , \"lng\": 12.030176053744734},\n"
				+ "{\"title\": \"punto66\" , \"type\": \"type21\" , \"image\": \"image66\" , \"description\": \"punto66\" , \"lat\": 45.583346017770396 , \"lng\": 12.058513383578292},\n"
				+ "{\"title\": \"punto67\" , \"type\": \"type22\" , \"image\": \"image67\" , \"description\": \"punto67\" , \"lat\": 45.56927212125291 , \"lng\": 12.049033987617152},\n"
				+ "{\"title\": \"punto68\" , \"type\": \"type22\" , \"image\": \"image68\" , \"description\": \"punto68\" , \"lat\": 45.53870624156194 , \"lng\": 11.947689297667406},\n"
				+ "{\"title\": \"punto69\" , \"type\": \"type22\" , \"image\": \"image69\" , \"description\": \"punto69\" , \"lat\": 45.55680062420791 , \"lng\": 12.003403284409615},\n"
				+ "{\"title\": \"punto70\" , \"type\": \"type23\" , \"image\": \"image70\" , \"description\": \"punto70\" , \"lat\": 45.47197266922963 , \"lng\": 12.003601218332463},\n"
				+ "{\"title\": \"punto71\" , \"type\": \"type23\" , \"image\": \"image71\" , \"description\": \"punto71\" , \"lat\": 45.51520672403784 , \"lng\": 12.110993609096893},\n"
				+ "{\"title\": \"punto72\" , \"type\": \"type23\" , \"image\": \"image72\" , \"description\": \"punto72\" , \"lat\": 45.4893505817553 , \"lng\": 12.023823257329228},\n"
				+ "{\"title\": \"punto73\" , \"type\": \"type24\" , \"image\": \"image73\" , \"description\": \"punto73\" , \"lat\": 45.58584400489325 , \"lng\": 12.023752656694327},\n"
				+ "{\"title\": \"punto74\" , \"type\": \"type24\" , \"image\": \"image74\" , \"description\": \"punto74\" , \"lat\": 45.57709270870573 , \"lng\": 12.042788352879624},\n"
				+ "{\"title\": \"punto75\" , \"type\": \"type24\" , \"image\": \"image75\" , \"description\": \"punto75\" , \"lat\": 45.51133286843642 , \"lng\": 12.097525277263822},\n"
				+ "{\"title\": \"punto76\" , \"type\": \"type25\" , \"image\": \"image76\" , \"description\": \"punto76\" , \"lat\": 45.45342171248739 , \"lng\": 12.055014869973801},\n"
				+ "{\"title\": \"punto77\" , \"type\": \"type25\" , \"image\": \"image77\" , \"description\": \"punto77\" , \"lat\": 45.53760431754114 , \"lng\": 12.04298880825372},\n"
				+ "{\"title\": \"punto78\" , \"type\": \"type25\" , \"image\": \"image78\" , \"description\": \"punto78\" , \"lat\": 45.5929841213372 , \"lng\": 12.047212239091571},\n"
				+ "{\"title\": \"punto79\" , \"type\": \"type26\" , \"image\": \"image79\" , \"description\": \"punto79\" , \"lat\": 45.50991836676509 , \"lng\": 12.044103289704662},\n"
				+ "{\"title\": \"punto80\" , \"type\": \"type26\" , \"image\": \"image80\" , \"description\": \"punto80\" , \"lat\": 45.494893566889424 , \"lng\": 12.038408592064489},\n"
				+ "{\"title\": \"punto81\" , \"type\": \"type26\" , \"image\": \"image81\" , \"description\": \"punto81\" , \"lat\": 45.501318969267295 , \"lng\": 12.139601994939447},\n"
				+ "{\"title\": \"punto82\" , \"type\": \"type27\" , \"image\": \"image82\" , \"description\": \"punto82\" , \"lat\": 45.43801497081965 , \"lng\": 12.017509543408044},\n"
				+ "{\"title\": \"punto83\" , \"type\": \"type27\" , \"image\": \"image83\" , \"description\": \"punto83\" , \"lat\": 45.50855918080159 , \"lng\": 12.007515771392622},\n"
				+ "{\"title\": \"punto84\" , \"type\": \"type27\" , \"image\": \"image84\" , \"description\": \"punto84\" , \"lat\": 45.60664885722226 , \"lng\": 12.047624496370371},\n"
				+ "{\"title\": \"punto85\" , \"type\": \"type28\" , \"image\": \"image85\" , \"description\": \"punto85\" , \"lat\": 45.469067277528566 , \"lng\": 12.061319758815625},\n"
				+ "{\"title\": \"punto86\" , \"type\": \"type28\" , \"image\": \"image86\" , \"description\": \"punto86\" , \"lat\": 45.571652452743265 , \"lng\": 12.042073521451247},\n"
				+ "{\"title\": \"punto87\" , \"type\": \"type28\" , \"image\": \"image87\" , \"description\": \"punto87\" , \"lat\": 45.555529240955224 , \"lng\": 11.98201381348573},\n"
				+ "{\"title\": \"punto88\" , \"type\": \"type29\" , \"image\": \"image88\" , \"description\": \"punto88\" , \"lat\": 45.518339524759924 , \"lng\": 11.99951394586192},\n"
				+ "{\"title\": \"punto89\" , \"type\": \"type29\" , \"image\": \"image89\" , \"description\": \"punto89\" , \"lat\": 45.52865722130849 , \"lng\": 12.022172967488407},\n"
				+ "{\"title\": \"punto90\" , \"type\": \"type29\" , \"image\": \"image90\" , \"description\": \"punto90\" , \"lat\": 45.58701792713692 , \"lng\": 12.049252093149974},\n"
				+ "{\"title\": \"punto91\" , \"type\": \"type30\" , \"image\": \"image91\" , \"description\": \"punto91\" , \"lat\": 45.509894660033176 , \"lng\": 12.045933863309607},\n"
				+ "{\"title\": \"punto92\" , \"type\": \"type30\" , \"image\": \"image92\" , \"description\": \"punto92\" , \"lat\": 45.46582911355349 , \"lng\": 12.054674474055528},\n"
				+ "{\"title\": \"punto93\" , \"type\": \"type30\" , \"image\": \"image93\" , \"description\": \"punto93\" , \"lat\": 45.46582823552638 , \"lng\": 12.067516225253847},\n"
				+ "{\"title\": \"punto94\" , \"type\": \"type31\" , \"image\": \"image94\" , \"description\": \"punto94\" , \"lat\": 45.49910809700883 , \"lng\": 11.989632378426968},\n"
				+ "{\"title\": \"punto95\" , \"type\": \"type31\" , \"image\": \"image95\" , \"description\": \"punto95\" , \"lat\": 45.496533721527555 , \"lng\": 12.122074126599546},\n"
				+ "{\"title\": \"punto96\" , \"type\": \"type31\" , \"image\": \"image96\" , \"description\": \"punto96\" , \"lat\": 45.490525382026085 , \"lng\": 12.071684184164278},\n"
				+ "{\"title\": \"punto97\" , \"type\": \"type32\" , \"image\": \"image97\" , \"description\": \"punto97\" , \"lat\": 45.519751392349924 , \"lng\": 11.998744903231744},\n"
				+ "{\"title\": \"punto98\" , \"type\": \"type32\" , \"image\": \"image98\" , \"description\": \"punto98\" , \"lat\": 45.54836366172513 , \"lng\": 11.998577226723853},\n"
				+ "{\"title\": \"punto99\" , \"type\": \"type32\" , \"image\": \"image99\" , \"description\": \"punto99\" , \"lat\": 45.49859708523185 , \"lng\": 11.959472039342204},\n"
				+ "{\"title\": \"punto100\" , \"type\": \"type33\" , \"image\": \"image100\" , \"description\": \"punto100\" , \"lat\": 45.555955962129815 , \"lng\": 12.107616125152232},\n"
				+ "{\"title\": \"punto101\" , \"type\": \"type33\" , \"image\": \"image101\" , \"description\": \"punto101\" , \"lat\": 45.522014 , \"lng\": 12.042762},\n"
				+ "{\"title\": \"punto102\" , \"type\": \"type33\" , \"image\": \"image102\" , \"description\": \"punto102\" , \"lat\": 45.522465 , \"lng\": 12.043497},\n"
				+ "{\"title\": \"punto103\" , \"type\": \"type34\" , \"image\": \"image103\" , \"description\": \"punto103\" , \"lat\": 45.523164 , \"lng\": 12.044119},\n"
				+ "{\"title\": \"punto104\" , \"type\": \"type34\" , \"image\": \"image104\" , \"description\": \"punto104\" , \"lat\": 45.523863 , \"lng\": 12.044999},\n"
				+ "{\"title\": \"punto105\" , \"type\": \"type34\" , \"image\": \"image105\" , \"description\": \"punto105\" , \"lat\": 45.524780 , \"lng\": 12.045353},\n"
				+ "{\"title\": \"punto106\" , \"type\": \"type35\" , \"image\": \"image106\" , \"description\": \"punto106\" , \"lat\": 45.525044 , \"lng\": 12.046287}\n"
				+ "]";
	}

}
