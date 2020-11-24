package cdl.com;

public class ItemOffer {
  public String getName() {
    return name;
  }

  public Integer getQuantity() {
    return quantity;
  }

   public Double getPrice() {
    return price;
  }

  public String name;
	public Integer quantity;
	public Double price;

	public ItemOffer(String Name, Integer Quantity, Double Price){
		name = Name;
		quantity = Quantity;
		price = Price;
	}
}
