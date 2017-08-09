package objects;

public class Item {

	private int overallPrice;
    private int sellPrice;
    private String name;
    private int id;
    private int sp; //sellPrice
    private String isMembers;
    private int buyPrice;
    
	public Item() {
		setOverallPrice(0);
		setSellPrice(0);
		setName("");
		setId(-1);
		setSp(0);
		setMembers("");
		setBuyPrice(0);
	}

	public int getOverallPrice() {
		return overallPrice;
	}

	public void setOverallPrice(int overallPrice) {
		this.overallPrice = overallPrice;
	}

	public int getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(int sellPrice) {
		this.sellPrice = sellPrice;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSp() {
		return sp;
	}

	public void setSp(int sp) {
		this.sp = sp;
	}

	public String isMembers() {
		return isMembers;
	}

	public void setMembers(String isMembers) {
		this.isMembers = isMembers;
	}

	public int getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(int buyPrice) {
		this.buyPrice = buyPrice;
	}
	
	public int getDifference() {
		if(buyPrice == 0|| sellPrice == 0) {
			return 0;
		}
		return (sellPrice - buyPrice);
	}
	@Override
	public String toString() {
		return "Name: "+name+" |Buy Price:" + buyPrice +" |Sell Price:"+sellPrice+" |Difference:"+(sellPrice - buyPrice);
	}
}
