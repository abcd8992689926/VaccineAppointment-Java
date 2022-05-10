package com.va.model;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.va.repository.SearchRepository;
import com.va.repository.SimulationRepository;
import com.va.repository.UserRepository;

@Component
public class SimulationModel {
	private String[]xing= {"李","王","張","劉","陳","楊","黃","趙","周","吳","徐","孫","朱","馬","胡","郭","林","何","高","梁","鄭","羅","宋","謝","唐","韓","曹","許","鄧","蕭","馮","曾","程","蔡","彭","潘","袁","於","董","餘","蘇","葉","呂","魏","蔣","田","杜","丁","沈","姜","範","江","傅","鐘","盧","汪","戴","崔","任","陸","廖","姚","方","金","邱","夏","譚","韋","賈","鄒","石","熊","孟","秦","閻","薛","侯","雷","白","龍","段","郝","孔","邵","史","毛","常","萬","顧","賴","武","康","賀","嚴","尹","錢","施","牛","洪","龔"};
	private String[]ming= {"世","中","仁","伶","佩","佳","俊","信","倫","偉","傑","儀","元","冠","凱","君","哲","嘉","國","士","如","娟","婷","子","孟","宇","安","宏","宗","宜","家","建","弘","強","彥","彬","德","心","志","忠","怡","惠","慧","慶","憲","成","政","敏","文","昌","明","智","曉","柏","榮","欣","正","民","永","淑","玉","玲","珊","珍","珮","琪","瑋","瑜","瑞","瑩","盈","真","祥","秀","秋","穎","立","維","美","翔","翰","聖","育","良","芬","芳","英","菁","華","萍","蓉","裕","豪","貞","賢","郁","鈴","銘","雅","雯","霖","青","靜","韻","鴻","麗","龍"};
	private int nYear = Calendar.getInstance().get(Calendar.YEAR);
    SimpleDateFormat dtf = new SimpleDateFormat("MMdd");
    String nMonthAndDay = dtf.format(System.currentTimeMillis());
	Random rand = new Random();
	
	@Autowired
	SimulationRepository simulationrepository;
	@Autowired
	PparameterModel pparametermodel;
	@Autowired
	UserRepository userrepository;
	@Autowired
	SearchRepository searchrepository;
	@Autowired
	JWTModel jwtmodel;
	//產生姓名
	public String name() {
		String name = xing[rand.nextInt(xing.length)]+ming[rand.nextInt(ming.length)]+ming[rand.nextInt(ming.length)];
		return name;
	}
	//產生1-12歲年分
	public String child() {
		int year = nYear-(rand.nextInt(12)+1);
		return year+monthAndDay(isLeap(year));
	}
	//產生13-18歲年分
	public String teenager() {
		int year = nYear-(rand.nextInt(6)+13);
		return year+monthAndDay(isLeap(year));
	}
	//產生19-40歲年分
	public String youth() {
		int year = nYear-(rand.nextInt(22)+19);
		return year+monthAndDay(isLeap(year));
	}
	//產生41-65歲年分
	public String middle() {
		int year = nYear-(rand.nextInt(25)+41);
		return year+monthAndDay(isLeap(year));
	}
	//產生66-120歲年分
	public String elderly() {
		int year = nYear-(rand.nextInt(56)+65);
		return year+monthAndDay(isLeap(year));
	}
	//產生月份和日期
	public String monthAndDay(boolean isLeap) {
		int i=31;
		int month = rand.nextInt(12)+1;
		if(isLeap&&month==2) i=29;
		if(month==4||month==6||month==9||month==11) i=30;
		if(month==2) i=28;
		int day = rand.nextInt(i)+1;
		return String.format("%02d", month)+String.format("%02d", day);
	}
	//閏年驗證
	public boolean isLeap(int year) {
	    return((((year)%4)==0&&((year)%100)!=0)||((year)%400)==0);
	}
	//產生健保卡號
	public String NHI() {
		long last;
		if(simulationrepository.getNHICount()!=0) {
			last=simulationrepository.getLastNHI();
		}else {
			last=0;
		}
		return String.format("%012d", ++last);
	}
	static String checkHead = "ABCDEFGHJKLMNPQRSTUVXYWZIO";
	static String cityHead[]= {"臺北市","臺中市","基隆市","臺南市","高雄市","新北市","宜蘭縣","桃園市","新竹縣","苗栗縣","臺中市","南投縣","彰化縣","雲林縣","嘉義縣","臺南市","高雄縣","屏東縣","花蓮縣","臺東縣","澎湖縣","陽明山","金門縣","連江縣","嘉義市","新竹市"};
	static String manufacturerHead[]= {"Moderna","BioNTech","AstraZeneca","MVC COVID-19 Vaccine"};
	static String phoneHead[]= {"0910","0911","0912","0913","0914","0915","0916","0917","0918","0919","0920","0921","0922","0923","0924","0925","0926","0927","0928","0929","0930","0931","0932","0933","0934","0935","0936","0937","0938","0939","0952","0953","0954","0955","0956","0958","0960","0961","0963","0968","0970","0971","0972","0982","0986","0987","0988","0989"};
	/*
	 * Moderna  0
	 * BioNTech  1
	 * AstraZeneca  2
	 * MVC COVID-19 Vaccine  3
	 */
	//產生ID
	public String ID() {
		String s="";
		char c;
		int checknum = 0; //產生前9碼的同時計算產生驗證碼
		int city[]=pparametermodel.getCity();
		// 產生第一個英文字母
		int t=(rand.nextInt(26)+65);
		int a=checkHead.indexOf((char)t);

		if(city[a]==0) {
			if(city[city[26]]==0) {
				for(int i=++city[26]; i<city.length-1; i++) {
					if(city[i]!=0) {
						a=i;
						pparametermodel.setCity(26, i);
						break;
					}
				}
			}else {
				a=city[26];
			}
			c=checkHead.charAt(a);
			t=c;
		}
		s+=(char)t;
		
		t=checkHead.indexOf((char)t)+10;
		
		checknum += t/10;
		checknum += t%10*9;

		// 產生第2個數字 (1~2)
		int sex[]=new int[2];
		sex[0]=pparametermodel.getMale();
		sex[1]=pparametermodel.getFemale();
		int r=rand.nextInt(2);
		switch(r) {
			case 0:
				if(sex[0]!=0) {
					t=1;
				}else {
					t=2;
				}
				break;
			case 1:
				if(sex[1]!=0) {
					t=2;
				}else {
					t=1;
				}
				break;
		}
		s += Integer.toString(t);
		checknum += t*8;

		// 產生後8碼
		for (int i=2; i<9; i++){
			s += Integer.toString(t = rand.nextInt(10));
			checknum += t*(9-i);
		}
		
		// 完成驗證碼計算
		checknum = (10-((checknum)%10))%10;
		s+=Integer.toString(checknum);
		if(userrepository.getIdCount(s)==0) {
			pparametermodel.setCity(a,--city[a]);
			switch(s.substring(1,2)) {
				case "1":
					pparametermodel.setMale(--sex[0]);
					break;
				case "2":
					pparametermodel.setFemale(--sex[1]);
					break;
			}
			return s;
		}else {
			return ID();
		}
	}
	//產生城市和廠牌
	public String[] vaccinated() {
		int manufacturer[][]=pparametermodel.getManufacturer();
		int last[]=pparametermodel.getLast();
		int t=(rand.nextInt(26)+65);
		int a=checkHead.indexOf((char)t);
		int m=rand.nextInt(4);
		String birthday=pparametermodel.getBirthday();
		String mdArrays[]=new String[3];
		mdArrays[0]=birthday.substring(4,8);
		int Intbirthday=Integer.parseInt(birthday.substring(0,4));
		int year=rand.nextInt(nYear-Intbirthday+1)+Intbirthday;
		
		if(manufacturer[a][m]==0) {
			if(manufacturer[last[0]][last[1]]==0) {
				forOut:
				for(int i=last[0]; i<manufacturer.length; i++) {
					for(int j=0; j<manufacturer[0].length; j++) {
						if(manufacturer[i][j]!=0) {
							a=i;
							m=j;
							pparametermodel.setLast(0, i);
							pparametermodel.setLast(1, j);
							break forOut;
						}
					}
				}
			}else {
				a=last[0];
				m=last[1];
			}
		}
		pparametermodel.setManufacturer(a, m, --manufacturer[a][m]);
		String zipCode[]=createZipCode(a);
		int count=simulationrepository.getZipCodeCount(Integer.parseInt(zipCode[0]));
		String HOSPID=simulationrepository.getHOSPID(Integer.parseInt(zipCode[0]), rand.nextInt(count));
		for(int i=1; i<3; i++) {
			mdArrays[i]=monthAndDay(isLeap(year));
		}
		while(mdArrays[1]==mdArrays[2]) {
			mdArrays[2]=monthAndDay(isLeap(year));
		}
		if(birthday.substring(0,4).equals(""+year)) {
			Arrays.sort(mdArrays);
			pparametermodel.setBirthday(year+mdArrays[0]);
		}else {
			mdArrays[0]="";
			Arrays.sort(mdArrays);
		}
		return new String[] {HOSPID, codeConverter(m), year+mdArrays[2], year+mdArrays[1], zipCode[0], zipCode[1]};
	}
	public void preference() {
		int people=pparametermodel.getTal();
		int tal=0;
		int preferenceTal[]=pparametermodel.getPreferenceTal();
		for(int i=0; i<preferenceTal.length; i++) {
			tal+=preferenceTal[i];
		}
		pparametermodel.createPreference(people);
		int preference[][]=pparametermodel.getPreference();
		int used[][]=new int[4][];
		int usedLast[]=new int[4];
		int last=0;
		for(int i=0; i<4; i++) {
			used[i]=new int[preferenceTal[i]];
		}
		for(int i=0; i<people; i++) {
			int r=rand.nextInt(preferenceTal.length);
			while(preferenceTal[r]==0) {
				if(preferenceTal[last]==0) last++;
				r=last;
			}
			used[r][usedLast[r]]=i;
			pparametermodel.setPreference(i, r, 1);
			usedLast[r]++;
			preferenceTal[r]--;
		}
		for(int i=0; i<preferenceTal.length; i++) {
			int pointer=preferenceTal[i];
			for(int j=0; j<pointer;) {
				int r=rand.nextInt(preference.length);
				if(preference[r][i]!=0) {
					do {
						r=rand.nextInt(preference.length);
					}while(!(binarySearch(used[i], r)));
				}
				if(preference[r][i]==0) {
					pparametermodel.setPreference(r, i, 1);
					used[i][usedLast[i]]=r;
					usedLast[i]++;
					j++;
				}
			}
		}
	}
	public String codeConverter(int manufacturer) {
		int s=0;
		switch(manufacturer) {
			case 0:
				s=1;
				break;
			case 1:
				s=2;
				break;
			case 2:
				s=8;
				break;
			case 3:
				s=16;
				break;
		}
		return Integer.toString(s);
	}
	public int codeConverter(int manufacturer[]) {
		int s=0;
		int p[]= {1, 2, 8, 16};
		for(int i=0; i<4; i++) {
			if(manufacturer[i]==1) {
				s+=p[i];
			}
		}
		return s;
	}
	public static boolean binarySearch(final int[] array, final int targetElement) {
		Arrays.sort(array);
		int start=0;
	    int end=array.length -1;
	    while (end >= start) {
	        final int middle=(end+start)>>>1;
	        if (targetElement==array[middle]) {
	            return true;
	        } else if (targetElement>array[middle]) {
	            start=middle+1;
	        } else {
	            end=middle-1;
	        }
	    }
	    return false;
	}
	public void birthday() {
		int age[]=pparametermodel.getAge();
		int r=rand.nextInt(5);
		String s="";
		while(age[r]==0) {
			if (age[age[5]]==0) {
				pparametermodel.setAge(5, ++age[5]);
			}
			r=age[5];
		}
		switch(r) {
			case 0:
				s=child();
				break;
			case 1:
				s=teenager();
				break;
			case 2:
				s=youth();
				break;
			case 3:
				s=middle();
				break;
			case 4:
				s=elderly();
				break;
		}
		pparametermodel.setAge(r, --age[r]);
		pparametermodel.setBirthday(s);
	}
	public String phoneNum() {
		String s="";
		s+=phoneHead[rand.nextInt(48)];
		for(int i=0; i<6; i++) {
			s+=rand.nextInt(10);
		}
		if(simulationrepository.getPhoneCount(s)==0) {
			return s;
		}else {
			return phoneNum();
		}
	}
	public String[] register(String ID) {
		ID=ID.substring(0,1);
		String zipCode[]=createZipCode(checkHead.indexOf(ID));
		String birthday=pparametermodel.getBirthday();
		int Intbirthday=Integer.parseInt(birthday.substring(0,4));
		int year=rand.nextInt(nYear-Intbirthday+1)+Intbirthday;
		String mdArrays[]=new String[2];
		mdArrays[0]=birthday.substring(4,8);
		mdArrays[1]=monthAndDay(isLeap(year));
		if(birthday.substring(0,4).equals(""+year)) {
			Arrays.sort(mdArrays);
			pparametermodel.setBirthday(year+mdArrays[0]);
		}
		return new String[] {year+mdArrays[1], zipCode[0], zipCode[1]};
	}
	public void start(int pointer) {
		String name=name();
		birthday();
		String NHI=NHI();
		String ID=ID();
		while(userrepository.getIdCount(ID)!=0) {
			ID=ID();
		}
		String birthday=pparametermodel.getBirthday();
		String phoneNum=phoneNum();
		int preference=codeConverter(pparametermodel.getPreference(pointer));
		simulationrepository.insertPBF(ID, NHI, name, birthday, 1);
		int RC[]=new int[2];
		RC[0]=pparametermodel.getZDLength();
		RC[1]=pparametermodel.getFDLength();
		int r=rand.nextInt(2);
		switch(r) {
			case 0:
				if(RC[0]!=0) {
					String register[]=register(ID);
					simulationrepository.insertRegister(ID, phoneNum, register[1], register[2], preference, register[0]);
					pparametermodel.setZDLength(--RC[0]);
				}else {
					String vaccinated[]=vaccinated();
					simulationrepository.insertVR(ID, 1, vaccinated[1], vaccinated[2], vaccinated[0]);
					simulationrepository.insertA(ID, 1, vaccinated[0], vaccinated[1], vaccinated[2]);
					simulationrepository.insertRegister(ID, phoneNum, vaccinated[4], vaccinated[5], preference, vaccinated[3]);
					pparametermodel.setFDLength(--RC[1]);
				}
				break;
			case 1:
				if(RC[1]!=0) {
					String vaccinated[]=vaccinated();
					simulationrepository.insertVR(ID, 1, vaccinated[1], vaccinated[2], vaccinated[0]);
					simulationrepository.insertA(ID, 1, vaccinated[0], vaccinated[1], vaccinated[2]);
					simulationrepository.insertRegister(ID, phoneNum, vaccinated[4], vaccinated[5], preference, vaccinated[3]);
					pparametermodel.setFDLength(--RC[1]);
				}else {
					String register[]=register(ID);
					simulationrepository.insertRegister(ID, phoneNum, register[1], register[2], preference, register[0]);
					pparametermodel.setZDLength(--RC[0]);
				}
				break;
		}
		RC[0]=pparametermodel.getZDLength();
		RC[1]=pparametermodel.getFDLength();
	}
	public void simulation(){
		int tal=pparametermodel.getTal();
		preference();
		for(int i=0; i<tal; i++) {
			start(i);
		}
	}
	public void randDose() {
		int a=rand.nextInt(100);
		int b=rand.nextInt(100);
		int c=rand.nextInt(100);
		int d=rand.nextInt(100);
		simulationrepository.updateDose("A", a);
		simulationrepository.updateDose("B", b);
		simulationrepository.updateDose("C", c);
		simulationrepository.updateDose("D", d);
	}
	public int[] getCountyDose(int county) {
		int sum[]=new int[26];
		for(int i=0; i<26; i++) {
			if(i!=21) {
				int zip[]=pparametermodel.getZipCode(i);
				sum[i]=searchrepository.getSumDose(zip);
			}
		}
		return sum;
	}
	public int[] getDistrictDose(int county) {
		int zip[]=pparametermodel.getZipCode(county);
		int sum[];
		if(zip[0]==300) {
			sum=new int[3];
			sum[0]=searchrepository.getSumDose("新竹市東區");
			sum[1]=searchrepository.getSumDose("新竹市北區");
			sum[2]=searchrepository.getSumDose("新竹市香山區");
		}else if(zip[0]==600) {
			sum=new int[2];
			sum[0]=searchrepository.getSumDose("嘉義市東區");
			sum[1]=searchrepository.getSumDose("嘉義市西區");
		}else {
			sum=new int[zip.length];
			for(int i=0; i<zip.length; i++) {
				sum[i]=searchrepository.getSumDose(zip[i]);
			}
		}
		return sum;
	}
	public int[] getHospDose() {
		int dose[]=new int[4];
		dose[0]=searchrepository.getLevelDose("A");
		dose[1]=searchrepository.getLevelDose("B");
		dose[2]=searchrepository.getLevelDose("C");
		dose[3]=searchrepository.getLevelDose("D");
		return dose;
	}
	public String[][] getDistrictDetail(int county, int district) {
		int zipCode[]=pparametermodel.getZipCode(county);
		String[][]NAD = null;
		if(zipCode[0]==300) {
			switch(district){
			    case 0:
			    	NAD=searchrepository.getNAD("新竹市東區");
			    	break;
			    case 1:
			    	NAD=searchrepository.getNAD("新竹市北區");
			    	break;
			    case 2:
			    	NAD=searchrepository.getNAD("新竹市香山區");
			    	break;
			}
		}else if(zipCode[0]==600) {
			switch(district){
			    case 0:
			    	NAD=searchrepository.getNAD("嘉義市東區");
			    	break;
			    case 1:
			    	NAD=searchrepository.getNAD("嘉義市西區");
			    	break;
			}
		}else {
			NAD=searchrepository.getNAD(zipCode[district]);
		}
		return NAD;
	}
	public String[] searchPersonal(String token) {
		String id=jwtmodel.decode(token);
		String national[]=searchrepository.getNational(id);
		national[4]=manufacturerConverter(Integer.parseInt(national[4]));
		return national;
	}
	public String[] createZipCode(int county) {
		String zipCodeArrays[]=pparametermodel.getZipCodeS(county);
		int r=rand.nextInt(zipCodeArrays.length);
		String zipCode=zipCodeArrays[r];
		int zipCodeI=0;
		if(county==25) {
			zipCodeI=300;
		}else if(county==24) {
			zipCodeI=600;
		}else if(county==21) {
			county=0;
			zipCodeArrays=pparametermodel.getZipCodeS(county);
			r=rand.nextInt(zipCodeArrays.length);
			zipCode=zipCodeArrays[r];
			zipCodeI=pparametermodel.getZipCode(county, r);
		}else {
			zipCodeI=pparametermodel.getZipCode(county, r);
		}
		return new String[] {zipCodeI+"", zipCode};
	}
	public String manufacturerConverter(int manufacturer) {
		String respone="";
		switch(manufacturer) {
			case 1:
				respone="Moderna";
				break;
			case 2:
				respone="BioNTech";
				break;
			case 3:
				respone="Moderna、BioNTech";
				break;
			case 8:
				respone="AstraZeneca";
				break;
			case 9:
				respone="Moderna、AstraZeneca";
				break;
			case 10:
				respone="BioNTech、AstraZeneca";
				break;
			case 11:
				respone="Moderna、BioNTech、AstraZeneca";
				break;
			case 16:
				respone="MVC COVID-19 Vaccine";
				break;
			case 17:
				respone="Moderna、MVC COVID-19 Vaccine";
				break;
			case 18:
				respone="BioNTech、MVC COVID-19 Vaccine";
				break;
			case 19:
				respone="Moderna、BioNTech、MVC COVID-19 Vaccine";
				break;
			case 24:
				respone="AstraZeneca、MVC COVID-19 Vaccine";
				break;
			case 25:
				respone="Moderna、AstraZeneca、MVC COVID-19 Vaccine";
				break;
			case 26:
				respone="BioNTech、AstraZeneca、MVC COVID-19 Vaccine";
				break;
			case 27:
				respone="Moderna、BioNTech、AstraZeneca、MVC COVID-19 Vaccine";
				break;
		}
		return respone;
	}
	public String[][] searchRecord(String token) {
		String id=jwtmodel.decode(token);
		String record[][];
		if(searchrepository.getRecordCount(id)==0) {
			record=new String[0][0];
		}else {
			record=searchrepository.getRecord(id);
			if(record[0][0]!=null) {
				for(int i=0; i<record.length; i++) {
					record[i][3]=manufacturerConverter(Integer.parseInt(record[i][3]));
				}	
			}
		}
		return record;
	}
	public String[] searchName(String token) {
		String id=jwtmodel.decode(token);
		return new String[] {id, searchrepository.getName(id)};
	}
	public void insertRegister(String token, int county, int district, String zipStr,String phoneNum, int manufacturer) {
		String id=jwtmodel.decode(token);
		int zipInt=pparametermodel.getZipCode(county, district);
	    SimpleDateFormat now = new SimpleDateFormat("YYYYMMdd");
	    simulationrepository.insertRegister(id, phoneNum, zipInt+"", zipStr, manufacturer, now.format(System.currentTimeMillis()));
	    userrepository.updateNation(id);
	}
	public String[] converterManufacturer(int manufacturer[]) {
		int manufacturerInt=codeConverter(manufacturer);
		return new String[] {manufacturerConverter(manufacturerInt), manufacturerInt+""};
	}
}