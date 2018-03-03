JavaAnnotationXmlDemo 是一个JAXB 的测试



@XmlRootElement   将一个Java类映射为一段XML的根节点

参数：name            定义这个根节点的名称

          namespace   定义这个根节点命名空间

@XmlAccessorType  定义映射这个类中的何种类型需要映射到XML。可接收四个参数，分别是：

      XmlAccessType.FIELD：映射这个类中的所有字段到XML

      XmlAccessType.PROPERTY：映射这个类中的属性（get/set方法）到XML

      XmlAccessType.PUBLIC_MEMBER：将这个类中的所有public的field或property同时映射到XML（默认）

      XmlAccessType.NONE：不映射

@XmlElement  指定一个字段或get/set方法映射到XML的节点。如，当一个类的XmlAccessorType 被标注为PROPERTY时，在某一个没有get/set方法的字段上标注此注解，即可将该字段映射到XML。

参数：defaultValue  指定节点默认值

         name             指定节点名称

         namespace    指定节点命名空间

         required         是否必须（默认为false）

         nillable           该字段是否包含 nillable="true" 属性（默认为false）

         type               定义该字段或属性的关联类型



@XmlAttribute  指定一个字段或get/set方法映射到XML的属性。

参数：name             指定属性名称

         namespace    指定属性命名空间

         required         是否必须（默认为false）

@XmlTransient  定义某一字段或属性不需要被映射为XML。如，当一个类的XmlAccessorType 被标注为PROPERTY时，在某一get/set方法的字段上标注此注解，那么该属性则不会被映射。

@XmlType 定义映射的一些相关规则

参数：propOrder        指定映射XML时的节点顺序

         factoryClass     指定UnMarshal时生成映射类实例所需的工厂类，默认为这个类本身

         factoryMethod  指定工厂类的工厂方法

         name               定义XML Schema中type的名称

         namespace      指定Schema中的命名空间

@XmlElementWrapper  为数组元素或集合元素定义一个父节点。如，类中有一元素为List items，若不加此注解，该元素将被映射为

    <items>...</items>

    <items>...</items>

这种形式，此注解可将这个元素进行包装，如：

    @XmlElementWrapper(name="items")
    @XmlElement(name="item")
    public List items;

将会生成这样的XML样式：

    <items>

        <item>...</item>

        <item>...</item>

    </items>

@XmlJavaTypeAdapter  自定义某一字段或属性映射到XML的适配器。如，类中包含一个接口，我们可以定义一个适配器（继承自 javax.xml.bind.annotation.adapters.XmlAdapter类），指定这个接口如何映射到XML。

@XmlSchema 配置整个包的namespace，这个注解需放在package-info.java文件中。




二.常用annotation使用说明
 

@XmlType
　　@XmlType用在class类的注解，常与@XmlRootElement，@XmlAccessorType一起使用。它有三个属性：name、propOrder、namespace，经常使用的只有前两个属性。如：

@XmlType(name = "basicStruct", propOrder = {
    "intValue",
    "stringArray",
    "stringValue"
)
在使用@XmlType的propOrder 属性时，必须列出JavaBean对象中的所有属性，否则会报错。
　　2.@XmlElement
　　@XmlElement将java对象的属性映射为xml的节点，在使用@XmlElement时，可通过name属性改变java对象属性在xml中显示的名称。如：

　　@XmlElement(name="Address")　　
　　private String yourAddress;
　　3.@XmlRootElement
　　@XmlRootElement用于类级别的注解，对应xml的跟元素，常与 @XmlType 和 @XmlAccessorType一起使用。如：

　　@XmlType
　　@XmlAccessorType(XmlAccessType.FIELD)
　　@XmlRootElement
　　public class Address {}
　　4.@XmlAttribute
　　@XmlAttribute用于把java对象的属性映射为xml的属性,并可通过name属性为生成的xml属性指定别名。如：
　　@XmlAttribute(name="Country")
　　private String state;
　　5.@XmlAccessorType
　　@XmlAccessorType用于指定由java对象生成xml文件时对java对象属性的访问方式。常与@XmlRootElement、@XmlType一起使用。它的属性值是XmlAccessType的4个枚举值，分　　　别为：

　　XmlAccessType.FIELD:java对象中的所有成员变量

　　XmlAccessType.PROPERTY：java对象中所有通过getter/setter方式访问的成员变量

　　XmlAccessType.PUBLIC_MEMBER：java对象中所有的public访问权限的成员变量和通过getter/setter方式访问的成员变量

　　XmlAccessType.NONE:java对象的所有属性都不映射为xml的元素

　　注意：@XmlAccessorType的默认访问级别是XmlAccessType.PUBLIC_MEMBER，因此，如果java对象中的private成员变量设置了public权限的getter/setter方法，就不要在　　　private变量上使用@XmlElement和@XmlAttribute注解，否则在由java对象生成xml时会报同一个属性在java类里存在两次的错误。同理，如果@XmlAccessorType的访问权限　　　为XmlAccessType.NONE，如果在java的成员变量上使用了@XmlElement或@XmlAttribute注解，这些成员变量依然可以映射到xml文件。

　　6.@XmlAccessorOrder
　　@XmlAccessorOrder用于对java对象生成的xml元素进行排序。它有两个属性值：

　　AccessorOrder.ALPHABETICAL：对生成的xml元素按字母书序排序

　　XmlAccessOrder.UNDEFINED:不排序

　　7.@XmlTransient
　　@XmlTransient用于标示在由java对象映射xml时，忽略此属性。即，在生成的xml文件中不出现此元素。

　　8.@XmlJavaTypeAdapter
　　@XmlJavaTypeAdapter常用在转换比较复杂的对象时，如map类型或者格式化日期等。使用此注解时，需要自己写一个adapter类继承XmlAdapter抽象类，并实现里面的方法。

　　@XmlJavaTypeAdapter(value=xxx.class),value为自己定义的adapter类

　　XmlAdapter如下：

public abstract class XmlAdapter<ValueType,BoundType> {
    // Do-nothing constructor for the derived classes.
    protected XmlAdapter() {}
    // Convert a value type to a bound type.
    public abstract BoundType unmarshal(ValueType v);
    // Convert a bound type to a value type.
    public abstract ValueType marshal(BoundType v);
 }
 
 摘自:http://blog.csdn.net/shenyunsese/article/details/42969021
