package com.fwzs.restful;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * @Description
 * @Author Tyler Yin
 * @Create 2020-03-09 17:57
 **/
@JsonInclude(JsonInclude.Include.ALWAYS)
public class DownloadResidentVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 姓名
     */
    private String name;

    /**
     * 身份证号
     */
    private String idCardNo;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 往来方式
     */
    private String inOrOut;

    /**
     * 有无赴湖北（武汉）等疫情重点地经历
     */
    private String visitWuhan;

    /**
     * 是否外地人返乡
     */
    private String localResident;

    /**
     * 近日家人有无发热症状
     */
    private String familyTemperature;

    /**
     * 人员审核状态，0表示待审核，1表示审核通过，2表示审核未通过
     */
    private String auditStatus;

    /**
     * 人员审核结果说明
     */
    private String auditRemark;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 区县
     */
    private String town;

    /**
     * 街道
     */
    private String street;

    /**
     * 地址
     */
    private String address;

    /**
     * 人脸图片
     */
    private String faceImg;

    /**
     * 人脸编码
     */
    private String faceId;

    /**
     * 工号
     */
    private String workNo;

    /**
     * 职位
     */
    private String position;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getInOrOut() {
        return inOrOut;
    }

    public void setInOrOut(String inOrOut) {
        this.inOrOut = inOrOut;
    }

    public String getVisitWuhan() {
        return visitWuhan;
    }

    public void setVisitWuhan(String visitWuhan) {
        this.visitWuhan = visitWuhan;
    }

    public String getLocalResident() {
        return localResident;
    }

    public void setLocalResident(String localResident) {
        this.localResident = localResident;
    }

    public String getFamilyTemperature() {
        return familyTemperature;
    }

    public void setFamilyTemperature(String familyTemperature) {
        this.familyTemperature = familyTemperature;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getAuditRemark() {
        return auditRemark;
    }

    public void setAuditRemark(String auditRemark) {
        this.auditRemark = auditRemark;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFaceImg() {
        return faceImg;
    }

    public void setFaceImg(String faceImg) {
        this.faceImg = faceImg;
    }

    public String getFaceId() {
        return faceId;
    }

    public void setFaceId(String faceId) {
        this.faceId = faceId;
    }

    public String getWorkNo() {
        return workNo;
    }

    public void setWorkNo(String workNo) {
        this.workNo = workNo;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
