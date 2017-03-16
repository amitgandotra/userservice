package com.ibps.openapi.config;

import com.jayway.restassured.filter.Filter;
import com.jayway.restassured.filter.FilterContext;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.FilterableRequestSpecification;
import com.jayway.restassured.specification.FilterableResponseSpecification;
import org.apache.commons.lang3.text.StrSubstitutor;

import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.zip.GZIPOutputStream;

public class MCRestAsuredFilter implements Filter {
//    public static String username = "kkrauth";
//    private static HashMap<String, String> samlValues = new HashMap<String, String>(4);
//    private StrSubstitutor substitutor = new StrSubstitutor(samlValues);
//
//    private static final String samlPrefix = "<saml:Assertion ID='assertionid' IssueInstant='2013-03-14T17:18:52Z' Version='2.0' xmlns:saml='urn:oasis:names:tc:SAML:2.0:assertion' xmlns:xs='http://www.w3.org/2001/XMLSchema'>"
//            + " <saml:Subject>"
//            + "   <saml:NameID Format='urn:oasis:names:tc:SAML:1.1:nameid-format:emailAddress'>${" + SamlField.NameID.toString() + "}</saml:NameID>" + " </saml:Subject>"
//            + " <saml:AttributeStatement xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'>";
//
//    private static final String samlSuffix = " </saml:AttributeStatement>" + "</saml:Assertion>";
//
//    enum SamlField {
//        NameID, mail
//    }
//
//    {
//        setDefaults();
//    }

    @Override
    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext ctx) {
//        String samlBase64 = "";
//        try {
//            String saml = getSamlString();
//            ByteArrayOutputStream bos = new ByteArrayOutputStream(saml.length());
//            GZIPOutputStream gos = new GZIPOutputStream(bos);
//            gos.write(saml.getBytes());
//            gos.close();
//            samlBase64 = Base64.getEncoder().encodeToString(bos.toByteArray());
//            bos.close();
//        } catch (Exception e) {
//
//        }

        requestSpec.contentType(ContentType.JSON)
                .and()
                .header("X-Requested-By", "something");
//                .header("username", username)
//                .header("Authorization", "\"".concat(samlBase64).concat("\""));

        return ctx.next(requestSpec, responseSpec);
    }


//    private String getSamlString() {
//        StringBuilder sb = new StringBuilder(samlPrefix);
//
//        for (SamlField samlField : SamlField.values()) {
//            sb.append("   <saml:Attribute Name='").append(samlField.toString()).append("'>")
//                    .append("     <saml:AttributeValue xsi:type='xs:string'>${").append(samlField.toString()).append("}</saml:AttributeValue>")
//                    .append("   </saml:Attribute>");
//        }
//
//        sb.append(samlSuffix).toString();
//
//        return substitutor.replace(sb.toString());
//    }
//
//    public static void setUserId(String id) {
//        samlValues.put(SamlField.NameID.toString(), id);
//    }
//
//    public static void setEmail(String email) {
//        samlValues.put(SamlField.mail.toString(), email);
//    }
//
//    public static void setDefaults() {
//        samlValues.put(SamlField.NameID.toString(), username);
//        samlValues.put(SamlField.mail.toString(), "kosta_krauth@mastercard.come");
//    }
}
