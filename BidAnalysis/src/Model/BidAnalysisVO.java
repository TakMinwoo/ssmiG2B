package Model;

public class BidAnalysisVO {

	private int no;
	private String bidNum;
	private String bidTrial;
	private String bidClass;
	private String bidName;
	private String bidAgency;
	private String bidDemand;
	private String bidMethod;
	private String bidStart;
	private String bidEnd;
	private String itemDetailNum;
	private int priceEstimate;
	private int baseAmount;
	private int bidAmount;
	private String winResult;

	public BidAnalysisVO() {

	}

	public BidAnalysisVO(int no, String bidNum, String bidTrial, String bidClass, String bidName, String bidAgency,
			String bidDemand, String bidMethod, String bidStart, String bidEnd, String itemDetailNum,
			int priceEstimate) {
		super();
		this.no = no;
		this.bidNum = bidNum;
		this.bidTrial = bidTrial;
		this.bidClass = bidClass;
		this.bidName = bidName;
		this.bidAgency = bidAgency;
		this.bidDemand = bidDemand;
		this.bidMethod = bidMethod;
		this.bidStart = bidStart;
		this.bidEnd = bidEnd;
		this.itemDetailNum = itemDetailNum;
		this.priceEstimate = priceEstimate;
	}

	public BidAnalysisVO(int no, String bidNum, String bidTrial, String bidClass, String bidName, String bidAgency,
			String bidDemand, String bidMethod, String bidStart, String bidEnd, String itemDetailNum, int priceEstimate,
			int baseAmount, int bidAmount, String winResult) {
		super();
		this.no = no;
		this.bidNum = bidNum;
		this.bidTrial = bidTrial;
		this.bidClass = bidClass;
		this.bidName = bidName;
		this.bidAgency = bidAgency;
		this.bidDemand = bidDemand;
		this.bidMethod = bidMethod;
		this.bidStart = bidStart;
		this.bidEnd = bidEnd;
		this.itemDetailNum = itemDetailNum;
		this.priceEstimate = priceEstimate;
		this.baseAmount = baseAmount;
		this.bidAmount = bidAmount;
		this.winResult = winResult;
	}

	public BidAnalysisVO(String bidNum, String bidTrial, String bidClass, String bidName, String bidAgency,
			String bidDemand, String bidMethod, String bidStart, String bidEnd, String itemDetailNum, int priceEstimate,
			int baseAmount, int bidAmount, String winResult) {
		super();
		this.bidNum = bidNum;
		this.bidTrial = bidTrial;
		this.bidClass = bidClass;
		this.bidName = bidName;
		this.bidAgency = bidAgency;
		this.bidDemand = bidDemand;
		this.bidMethod = bidMethod;
		this.bidStart = bidStart;
		this.bidEnd = bidEnd;
		this.itemDetailNum = itemDetailNum;
		this.priceEstimate = priceEstimate;
		this.baseAmount = baseAmount;
		this.bidAmount = bidAmount;
		this.winResult = winResult;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getBidNum() {
		return bidNum;
	}

	public void setBidNum(String bidNum) {
		this.bidNum = bidNum;
	}

	public String getBidTrial() {
		return bidTrial;
	}

	public void setBidTrial(String bidTrial) {
		this.bidTrial = bidTrial;
	}

	public String getBidClass() {
		return bidClass;
	}

	public void setBidClass(String bidClass) {
		this.bidClass = bidClass;
	}

	public String getBidName() {
		return bidName;
	}

	public void setBidName(String bidName) {
		this.bidName = bidName;
	}

	public String getBidAgency() {
		return bidAgency;
	}

	public void setBidAgency(String bidAgency) {
		this.bidAgency = bidAgency;
	}

	public String getBidDemand() {
		return bidDemand;
	}

	public void setBidDemand(String bidDemand) {
		this.bidDemand = bidDemand;
	}

	public String getBidMethod() {
		return bidMethod;
	}

	public void setBidMethod(String bidMethod) {
		this.bidMethod = bidMethod;
	}

	public String getBidStart() {
		return bidStart;
	}

	public void setBidStart(String bidStart) {
		this.bidStart = bidStart;
	}

	public String getBidEnd() {
		return bidEnd;
	}

	public void setBidEnd(String bidEnd) {
		this.bidEnd = bidEnd;
	}

	public String getItemDetailNum() {
		return itemDetailNum;
	}

	public void setItemDetailNum(String itemDetailNum) {
		this.itemDetailNum = itemDetailNum;
	}

	public int getPriceEstimate() {
		return priceEstimate;
	}

	public void setPriceEstimate(int priceEstimate) {
		this.priceEstimate = priceEstimate;
	}

	public int getBaseAmount() {
		return baseAmount;
	}

	public void setBaseAmount(int baseAmount) {
		this.baseAmount = baseAmount;
	}

	public int getBidAmount() {
		return bidAmount;
	}

	public void setBidAmount(int bidAmount) {
		this.bidAmount = bidAmount;
	}

	public String getWinResult() {
		return winResult;
	}

	public void setWinResult(String winResult) {
		this.winResult = winResult;
	}

}
