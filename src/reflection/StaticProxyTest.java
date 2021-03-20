package reflection;


/**
 * 靜態代理舉例
 *
 *特點:代理類核備代理類在編譯期間，就確定下來了
 *
 */

interface ClothFactory{

    void produceCloth();

}


//代理類
class ProxyClothFactory implements ClothFactory{

  private ClothFactory factory;//用被帶理類物件進行實例化

  public ProxyClothFactory(ClothFactory factory){
      this.factory = factory;
  }

  @Override
  public void produceCloth() {
      System.out.println("代理工廠做一些準備工作");

      factory.produceCloth();

      System.out.println("代理工廠做一些後續的收尾工作");

  }
}

//被代理類
class NikeClothFactory implements ClothFactory{

  @Override
  public void produceCloth() {
      System.out.println("Nike工廠產生一批運動服");
  }
}


public class StaticProxyTest {
	public static void main(String[] args) {
		//創建被代理類的物件
		NikeClothFactory nikeClothFactory = new NikeClothFactory();
		
		//創建代理類的物件
		ClothFactory clothFactory =new ProxyClothFactory(nikeClothFactory);
		
		clothFactory.produceCloth();
	}
	
}
