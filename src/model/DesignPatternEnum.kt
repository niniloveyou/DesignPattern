package model

enum class DesignPatternEnum(val title: String,
                             val supportCreate: Boolean,
                             val supportUpdate: Boolean) {

    // 创建型模式

    /** 单例模式 **/
    Singleton("单例模式", true, true),

    /** 建造者模式 **/
    Builder("建造者模式", true, true),

    /** 工厂模式 **/
    Factory("工厂模式", true, false),

    /** 原型模式 **/
    Prototype("原型模式", true, true),


    // 结构型模式

    /** 适配器模式 **/
    Adapter("适配器模式", true, true),

    /** 装饰者模式 **/
    Decorator("装饰者模式", true, true),

    /** 代理模式 **/
    Proxy("代理模式", true, true),

    /** 外观模式 **/
    Facade("外观模式", true, true),

    /** 桥接模式 **/
    Bridge("桥接模式", true, true),

    /** 组合模式 **/
    Composite("组合模式", true, true),

    /** 享元模式 **/
    FlyWeight("享元模式", true, true),


    // 行为型模式

    /** 策略模式 **/
    Strategy("策略模式", true, true),

    /** 模板模式 **/
    Template("模板模式", true, true),

    /** 观察者模式 **/
    Observer("观察者模式", true, true),

    /** 迭代模式 **/
    Iterator("迭代模式", true, true),

    /** 责任链模式 **/
    Chain("责任链模式", true, true),

    /** 命令模式 **/
    Command("命令模式", true, true),

    /** 备忘录模式 **/
    Memento("备忘录模式", true, true),

    /** 状态模式 **/
    State("状态模式", true, true),

    /** 访问者模式 **/
    Visitor("访问者模式", true, true),

    /** 中介者模式 **/
    Mediator("中介者模式", true, true),

    /** 解释器模式 **/
    Interpreter("解释器模式", true, true),

    /** 委托模式 **/
    Delegation("委托模式", true, true),
}