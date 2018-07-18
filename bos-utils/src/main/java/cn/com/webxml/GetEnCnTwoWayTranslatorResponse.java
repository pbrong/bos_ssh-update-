
package cn.com.webxml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="getEnCnTwoWayTranslatorResult" type="{http://WebXml.com.cn/}ArrayOfString" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "getEnCnTwoWayTranslatorResult"
})
@XmlRootElement(name = "getEnCnTwoWayTranslatorResponse")
public class GetEnCnTwoWayTranslatorResponse {

    protected ArrayOfString getEnCnTwoWayTranslatorResult;

    /**
     * 获取getEnCnTwoWayTranslatorResult属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getGetEnCnTwoWayTranslatorResult() {
        return getEnCnTwoWayTranslatorResult;
    }

    /**
     * 设置getEnCnTwoWayTranslatorResult属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setGetEnCnTwoWayTranslatorResult(ArrayOfString value) {
        this.getEnCnTwoWayTranslatorResult = value;
    }

}
