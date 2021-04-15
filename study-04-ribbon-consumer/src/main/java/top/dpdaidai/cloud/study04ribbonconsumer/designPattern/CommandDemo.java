package top.dpdaidai.cloud.study04ribbonconsumer.designPattern;

/**
 * @Author chenpantao
 * @Date 4/15/21 3:06 PM
 * @Version 1.0
 */
public class CommandDemo {
//    public static void main(String[] args) {
//        Receiver receiver = new Receiver();
//        Command command = new Command(receiver);
//
//        Invoker invoker = new Invoker();
//        invoker.setCommand(command);
//
//        // 客户端通过调用者来执行命令
//        // 调用者 Invoker 与 接收者 Receiver  通过 Command 命令接口解耦
//        // 就像老板通过主管通知我干活 ?
//        invoker.action();
//    }

}

//接收者
class Receiver {
    public void action() {
        //真正的业务逻辑
    }
}

//命令的实现
class Command {
    private Receiver receiver;

    public Command(Receiver receiver) {
        this.receiver = receiver;
    }

    public void execute() {
        this.receiver.action();
    }
}

//客户端调用者
class Invoker {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void action() {
        this.command.execute();
    }
}
