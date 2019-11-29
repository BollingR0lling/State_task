public class Main {
    public static void main(String[] args) {
        DocumentContext context = new DocumentContext();
        context.setCash(250);
        context.setState(new CasheInState());
        context.cashIn();

        context.setDevice("Флешка");
        context.chooseDevice();

        context.setDoc("Документ");
        context.chooseDoc();
        context.printDoc();

        context.setNeedPrint(true);
        context.chooseState();
        context.setDoc("Документ 2");
        context.chooseDoc();
        context.printDoc();
        context.setNeedPrint(false);
        context.chooseState();

        context.cashOut();
    }
}
class DocumentContext {
    private int Cash;
    private String Doc;
    private State state;
    private String device;
    private boolean needPrint;

    public void setState(State state) {this.state = state;}

    public boolean isNeedPrint() {return needPrint;}
    public void setNeedPrint(boolean needPrint) {this.needPrint = needPrint;}
    public String getDoc() {return Doc;}
    public void setDoc(String doc) {Doc = doc;}
    public int getCash() {return Cash;}
    public void setCash(int cash) {Cash = cash;}

    public String getDevice() {return device;}
    public void setDevice(String device) {this.device = device;}

    public void cashIn(){state.cashIn(this);}
    public void chooseState(){state.chooseState(this);}
    public void chooseDevice(){state.chooseDevice(this);}
    public void chooseDoc(){state.chooseDoc(this);}
    public void printDoc(){state.printDoc(this);}
    public void cashOut(){state.cashOut(this);}

}

interface State{
    void cashIn(DocumentContext context);
    void chooseDevice(DocumentContext context);
    void chooseDoc(DocumentContext context);
    void chooseState(DocumentContext context);
    void printDoc(DocumentContext context);
    void cashOut(DocumentContext context);
}

class CasheInState implements State{

    @Override
    public void cashIn(DocumentContext context) {
        context.setCash(250);
        System.out.println("Пользователь внес " + context.getCash() + " рублей");
        context.setState(new DeviceState());
    }

    @Override
    public void chooseDevice(DocumentContext context) { }

    @Override
    public void chooseDoc(DocumentContext context) { }

    @Override
    public void chooseState(DocumentContext context) { }

    @Override
    public void printDoc(DocumentContext context) { }

    @Override
    public void cashOut(DocumentContext context) { }
}

class DeviceState implements State{

    @Override
    public void cashIn(DocumentContext context) { }

    @Override
    public void chooseDevice(DocumentContext context) {
        System.out.println("Пользователь выбрал носитель: " + context.getDevice());
        context.setState(new chooseDocState());
    }

    @Override
    public void chooseDoc(DocumentContext context) { }

    @Override
    public void chooseState(DocumentContext context) { }

    @Override
    public void printDoc(DocumentContext context) { }

    @Override
    public void cashOut(DocumentContext context) { }
}

class chooseDocState implements State{

    @Override
    public void cashIn(DocumentContext context) { }

    @Override
    public void chooseDevice(DocumentContext context) { }

    @Override
    public void chooseDoc(DocumentContext context)
    {
        System.out.println("Пользователь будет печатать " + context.getDoc());
        context.setState(new printDocState());
    }

    @Override
    public void chooseState(DocumentContext context) { }

    @Override
    public void printDoc(DocumentContext context) { }

    @Override
    public void cashOut(DocumentContext context) { }
}

class printDocState implements State{

    @Override
    public void cashIn(DocumentContext context) { }

    @Override
    public void chooseDevice(DocumentContext context) { }

    @Override
    public void chooseDoc(DocumentContext context) { }

    @Override
    public void chooseState(DocumentContext context) { }

    @Override
    public void printDoc(DocumentContext context) {
        System.out.println("Пользователь распечатал: " + context.getDoc());
        context.setState(new chooseStates());
    }

    @Override
    public void cashOut(DocumentContext context) { }
}

class chooseStates implements State{

    @Override
    public void cashIn(DocumentContext context) { }

    @Override
    public void chooseDevice(DocumentContext context) { }

    @Override
    public void chooseDoc(DocumentContext context) { }

    @Override
    public void printDoc(DocumentContext context) { }

    @Override
    public void chooseState(DocumentContext context) {
        if(context.isNeedPrint()){
            context.setCash(context.getCash() - 20);
            System.out.println("Пользователь выбрал новую печать");
            context.setState(new chooseDocState());
        } else {
            context.setCash(context.getCash() - 40);
            System.out.println("Пользователь хочет завершить работу");
            context.setState(new cashOutState());
        }
    }

    @Override
    public void cashOut(DocumentContext context) { }
}

class cashOutState implements State{

    @Override
    public void cashIn(DocumentContext context) { }

    @Override
    public void chooseDevice(DocumentContext context) { }

    @Override
    public void chooseDoc(DocumentContext context) { }

    @Override
    public void chooseState(DocumentContext context) { }

    @Override
    public void printDoc(DocumentContext context) { }

    @Override
    public void cashOut(DocumentContext context) {
        System.out.println("Ваша сдача: " + context.getCash());
    }
}