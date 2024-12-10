//package procedures;
//
//import java.util.List;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//
//import pageobjects.CartPage;
//import pageobjects.ProductCataloguePage;
//
//public class ProductCatalogueProcedures {
//	public static void addItemsLessThanTenDollarsToCart(WebDriver driver) {
//        ProductCataloguePage productCataloguePage = new ProductCataloguePage(driver);
//        List<WebElement> products = productCataloguePage.getProductNames();
//        List<WebElement> prices = productCataloguePage.getProductPrices();
//        List<WebElement> addToCartButtons = productCataloguePage.getAddToCartButtons();
//
//        for (int i = 0; i < prices.size(); i++) {
//            String priceText = prices.get(i).getText().replace("$", "");
//            double price = Double.parseDouble(priceText);
//            if (price < 10.0) {
//                productCataloguePage.addItemToCart(addToCartButtons.get(i));
//            }
//        }
//        productCataloguePage.goToCart().click();
//     
//    
//    }
//
//
//}