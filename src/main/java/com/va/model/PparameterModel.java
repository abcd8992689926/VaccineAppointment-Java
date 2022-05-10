package com.va.model;

import java.util.Arrays;

import org.springframework.stereotype.Component;

@Component
public class PparameterModel {
	private int male;
	private int female;
	private int city[]=new int[27];
	private int manufacturer[][]=new int[26][4];
	private int last[]=new int[2];
	private int tal;
	private int preferenceTal[]=new int[4];
	private int preference[][];
	private int age[]=new int[6];
	private String birthday="";
	private int ZDLength=0;
	private int FDLength=0;
	private int zipCode[][]={{100,103,104,105,106,108,110,111,112,114,115,116},{411,412,413,414,420,421,422,423,424,426,427,428,429,432,433,434,435,436,437,438,439},
		{200,201,202,203,204,205,206},{710,711,712,713,714,715,716,717,718,719,720,721,722,723,724,725,726,727,730,731,732,733,734,735,736,737,741,742,743,744,745},
		{814,815,820,821,822,823,824,825,826,827,828,829,830,831,832,833,840,842,843,844,845,846,847,848,849,851,852},
		{207,208,220,221,222,223,224,226,227,228,231,232,233,234,235,236,237,238,239,241,242,243,244,247,248,249,251,252,253},
		{260,261,262,263,264,265,266,267,268,269,270,272},{320,324,325,326,327,328,330,333,334,335,336,337,338},
		{302,303,304,305,306,307,308,310,311,312,313,314,315},{350,351,352,353,354,356,357,358,360,361,362,363,364,365,366,367,368,369},
		{400,401,402,403,404,406,407,408},{540,541,542,544,545,546,551,552,553,555,556,557,558},{500,502,503,504,505,506,507,508,509,510,511,512,513,514,515,516,520,521,522,523,524,525,526,527,528,530},
		{630,631,632,633,634,635,636,637,638,640,643,646,647,648,649,651,652,653,654,655},{602,603,604,605,606,607,608,611,612,613,614,615,616,621,622,623,624,625},{700,701,702,704,708,709},
		{800,801,802,803,804,805,806,807,811,812,813},{900,901,902,903,904,905,906,907,908,909,911,912,913,920,921,922,923,924,925,926,927,928,929,931,932,940,941,942,943,944,945,946,947},
		{970,971,972,973,974,975,976,977,978,979,981,982,983},{950,951,952,953,954,955,956,957,958,959,961,962,963,964,965,966},
		{880,881,882,883,884,885},{0},{890,891,892,893,894},{209,210,211,212},{600},{300}};
	private String zipCodeS[][]= {{"中正區", "大同區", "中山區", "松山區", "大安區", "萬華區", "信義區", "士林區", "北投區", "內湖區", "南港區", "文山區"},
		{"太平區", "大里區", "霧峰區", "烏日區", "豐原區", "后里區", "石岡區", "東勢區", "和平區", "新社區", "潭子區", "大雅區", "神岡區", "大肚區", "沙鹿區", "龍井區", "梧棲區", "清水區", "大甲區", "外埔區", "大安區"},
		{"仁愛區", "信義區", "中正區", "中山區", "安樂區", "暖暖區", "七堵區"},
		{"永康區", "歸仁區", "新化區", "左鎮區", "玉井區", "楠西區", "南化區", "仁德區", "關廟區", "龍崎區", "官田區", "麻豆區", "佳里區", "西港區", "七股區", "將軍區", "學甲區", "北門區", "新營區", "後壁區", "白河區", "東山區", "六甲區", "下營區", "柳營區", "鹽水區", "善化區", "大內區", "山上區", "新市區", "安定區"},
		{"仁武區", "大社區", "岡山區", "路竹區", "阿蓮區", "田寮區", "燕巢區", "橋頭區", "梓官區", "彌陀區", "永安區", "湖內區", "鳳山區", "大寮區", "林園區", "鳥松區", "大樹區", "旗山區", "美濃區", "六龜區", "內門區", "杉林區", "甲仙區", "桃源區", "那瑪夏", "茂林區", "茄萣區"},
		{"萬里區", "金山區", "板橋區", "汐止區", "深坑區", "石碇區", "瑞芳區", "平溪區", "雙溪區", "貢寮區", "新店區", "坪林區", "烏來區", "永和區", "中和區", "土城區", "三峽區", "樹林區", "鶯歌區", "三重區", "新莊區", "泰山區", "林口區", "蘆洲區", "五股區", "八里區", "淡水區", "三芝區", "石門區"},
		{"宜蘭市", "頭城鎮", "礁溪鄉", "壯圍鄉", "員山鄉", "羅東鎮", "三星鄉", "大同鄉", "五結鄉", "冬山鄉", "蘇澳鎮", "南澳鄉"},
		{"中壢區", "平鎮區", "龍潭區", "楊梅區", "新屋區", "觀音區", "桃園區", "龜山區", "八德區", "大溪區", "復興區", "大園區", "蘆竹區"},
		{"竹北市", "湖口鄉", "新豐鄉", "新埔鎮", "關西鎮", "芎林鄉", "寶山鄉", "竹東鎮", "五峰鄉", "橫山鄉", "尖石鄉", "北埔鄉","峨眉鄉"},
		{"竹南鎮", "頭份市", "三灣鄉", "南庄鄉", "獅潭鄉", "後龍鎮", "通霄鎮", "苑裡鎮", "苗栗市", "造橋鄉", "頭屋鄉", "公館鄉", "大湖鄉", "泰安鄉", "銅鑼鄉", "三義鄉", "西湖鄉", "卓蘭鎮"},
		{"中區", "東區", "南區", "西區", "北區", "北屯區", "西屯區", "南屯區"},
		{"南投市", "中寮鄉", "草屯鎮", "國姓鄉", "埔里鎮", "仁愛鄉", "名間鄉", "集集鎮", "水里鄉", "魚池鄉", "信義鄉", "竹山鎮", "鹿谷鄉"},
		{"彰化市", "芬園鄉", "花壇鄉", "秀水鄉", "鹿港鎮", "福興鄉", "線西鄉", "和美鎮", "伸港鄉", "員林市", "社頭鄉", "永靖鄉", "埔心鄉", "溪湖鎮", "大村鄉", "埔鹽鄉", "田中鎮", "北斗鎮", "田尾鄉", "埤頭鄉", "溪州鄉", "竹塘鄉", "二林鎮", "大城鄉", "芳苑鄉", "二水鄉"},
		{"斗南鎮", "大埤鄉", "虎尾鎮", "土庫鎮", "褒忠鄉", "東勢鄉", "臺西鄉", "崙背鄉", "麥寮鄉", "斗六市", "林內鄉", "古坑鄉", "莿桐鄉", "西螺鎮", "二崙鄉", "北港鎮", "水林鄉", "口湖鄉", "四湖鄉", "元長鄉"},
		{"番路鄉", "梅山鄉", "竹崎鄉", "阿里山", "中埔鄉", "大埔鄉", "水上鄉", "鹿草鄉", "太保市", "朴子市", "東石鄉", "六腳鄉", "新港鄉", "民雄鄉", "大林鎮", "溪口鄉", "義竹鄉", "布袋鎮"},
		{"中西區", "東區", "南區", "北區", "安平區", "安南區"},
		{"新興區", "前金區", "苓雅區", "鹽埕區", "鼓山區", "旗津區", "前鎮區", "三民區", "楠梓區", "小港區", "左營區"},
		{"屏東市", "三地門", "霧臺鄉", "瑪家鄉", "九如鄉", "里港鄉", "高樹鄉", "鹽埔鄉", "長治鄉", "麟洛鄉", "竹田鄉", "內埔鄉", "萬丹鄉", "潮州鎮", "泰武鄉", "來義鄉", "萬巒鄉", "崁頂鄉", "新埤鄉", "南州鄉", "林邊鄉", "東港鎮", "琉球鄉", "佳冬鄉", "新園鄉", "枋寮鄉", "枋山鄉", "春日鄉", "獅子鄉", "車城鄉", "牡丹鄉", "恆春鎮", "滿州鄉"},
		{"花蓮市", "新城鄉", "秀林鄉", "吉安鄉", "壽豐鄉", "鳳林鎮", "光復鄉", "豐濱鄉", "瑞穗鄉", "萬榮鄉", "玉里鎮", "卓溪鄉", "富里鄉"},
		{"臺東市", "綠島鄉", "蘭嶼鄉", "延平鄉", "卑南鄉", "鹿野鄉", "關山鎮", "海端鄉", "池上鄉", "東河鄉", "成功鎮", "長濱鄉", "太麻里鄉", "金峰鄉", "大武鄉", "達仁鄉"},
		{"馬公市", "西嶼鄉", "望安鄉", "七美鄉", "白沙鄉", "湖西鄉"},
		{""},
		{"金沙鎮", "金湖鎮", "金寧鄉", "金城鎮", "烈嶼鄉"},
		{"南竿鄉", "北竿鄉", "莒光鄉", "東引鄉"},
		{"東區", "西區"},
		{"東區", "北區", "香山區"}};
	public void setMale(int male) {
		this.male=male;
	}
	public int getMale() {
		return male;
	}
	public void setFemale(int female) {
		this.female=female;
	}
	public int getFemale() {
		return female;
	}
	public void setCity(int city, int value) {
		this.city[city]=value;
	}
	public int[] getCity() {
		return city;
	}
	public void setManufacturer(int city, int manufacturer, int value) {
		this.manufacturer[city][manufacturer]=value;
	}
	public int[][] getManufacturer(){
		return manufacturer;
	}
	public void setLast(int pointer, int value) {
		this.last[pointer]=value;
	}
	public int[] getLast() {
		return last;
	}
	public void setTal(int tal) {
		this.tal=tal;
	}
	public int getTal() {
		return tal;
	}
	public void setPreferenceTal(int pointer, int value) {
		this.preferenceTal[pointer]=value;
	}
	public int[] getPreferenceTal() {
		return preferenceTal;
	}
	public void createPreference(int value) {
		preference=new int[value][4];
	}
	public int[][] getPreference() {
		return preference;
	}
	public int[] getPreference(int row) {
		return preference[row];
	}
	public void setPreference(int row, int pointer, int value) {
		this.preference[row][pointer]=value;
	}
	public void setAge(int pointer, int value) {
		this.age[pointer]=value;
	}
	public int[] getAge() {
		return this.age;
	}
	public int[] getZipCode(int county){
		return this.zipCode[county];
	}
	public int getZipCode(int county, int district) {
		return this.zipCode[county][district]; 
	}
	public String[] getZipCodeS(int county){
		return this.zipCodeS[county];
	}
	public void setBirthday(String birthday) {
		this.birthday=birthday;
	}
	public String getBirthday() {
		return this.birthday;
	}
	public void setZDLength(int value) {
		this.ZDLength=value;
	}
	public int getZDLength() {
		return ZDLength;
	}
	public void setFDLength(int value) {
		this.FDLength=value;
	}
	public int getFDLength() {
		return FDLength;
	}
}
