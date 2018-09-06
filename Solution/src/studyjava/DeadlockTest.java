package studyjava;

//public class DeadlockTest {  
//    public void recursive(){  
//        this.businessLogic();  
//    }  
//    public synchronized void businessLogic(){  
//        System.out.println("处理业务逻辑");  
//    System.out.println("保存到数据库");  
//        this.recursive();  
//    }
//    public static void main(String[] args) {
//    	DeadlockTest dt = new DeadlockTest();
//    	dt.recursive();
//    }
//}  
public class DeadlockTest {  
    private void recursive(){  
        this.businessLogic();  
    }  
    public synchronized void businessLogic(){  
        System.out.println("处理业务逻辑");  
    System.out.println("保存到数据库");  
        this.recursive();  
    }  
  public static void main(String[] args) {
	DeadlockTest dt = new DeadlockTest();
	dt.recursive();
}
}  