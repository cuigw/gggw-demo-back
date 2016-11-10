/**
 * 手机号码校验
 * @param sMobile
 */
function validateMobile(sMobile) {
	//手机号码规则(11位)
	var regMobile = /^1\d{10}$/;
	if(!regMobile.test(sMobile)) {
		return "手机号码填写错误";
	} else {
		return "success";
	}
}

/**
 * QQ号码校验
 * @param QQ
 */
function validateQQ(qq) {
	//手机号码规则(11位)
	var regQQ = /^\d{1,11}$/;
	if(!regQQ.test(qq)) {
		return "QQ号码填写错误";
	} else {
		return "success";
	}
}

//验证手机号码
function validateMphone(field) {
	$(field).val(field.value.replace(/\s/g, ''));
	if (/^1\d{10}$/.test(field.value)) {
		field.handleSuccess();
		$(field).data('localPass', true);
		if($('#getCode').find("#seconds").length == 0){
			if(null != $("#mobile").data("remotePass") && !$("#mobile").data("remotePass")){
				$('#getCode').prop('disabled', true);
			}else{
				$('#getCode').prop('disabled', false);
			}
		}
	} else {
		field.handleFail('手机号码填写错误');
		$(field).data('localPass', false);
		if($('#getCode').find("#seconds").length == 0){
			$('#getCode').prop('disabled', true);
		}
	}
}

//验证验证码
function validateCode(validateCode){
	if (/^\d{4}$/.test(validateCode)) {
		return "success";
	} else {
		return "验证码填写错误";
	}
}

/**
 * 姓名校验
 * @param sName
 */
function validateName(sName) {
	//姓名规则(2-20个字符)
	var sLength = getLength(sName);
	if (sLength <= 20 && sLength >= 2) {
		return "success";
	} else {
		return "姓名不符合规范";
	}
}

/**
 * 身份证号校验
 * @param cidStr
 */
function validateCID(cidStr) {
 	var iSum = 0;
	//身份证正则表达式(18位)
	//var regCID = /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{4}$/;
	cidStr = cidStr.replace(/x/g, "X");

	if (!/^\d{17}(\d|X)$/i.test(cidStr))
		return "身份证号码填写错误";//身份证号码位数有误
	//cidStr = cidStr.replace(/x$/i, "a");

	if (cidCity[parseInt(cidStr.substr(0,2))] == null)
		return "身份证号码填写错误";//地区编码有误

	try{
		var today = new Date();
		var delt = parseInt(today.getFullYear()) - parseInt(cidStr.substr(6,4));
		
		if (delt > 150)
			return "身份证号码填写错误";//你活得太久了，快去申请吉尼斯吧
		if (delt <= 0)
			return "身份证号码填写错误";//好有远见，这么快就在为后代申请身份证了啊，佩服！

		var sBirthday = cidStr.substr(6,4) + "-" + Number(cidStr.substr(10,2)) + "-" + Number(cidStr.substr(12,2));
		var d = new Date(sBirthday.replace(/-/g, "/"));
		if (sBirthday != (d.getFullYear() + "-" + (d.getMonth()+1) + "-" + d.getDate()))
			return "身份证号码填写错误";//生日有误

		if (calcAge(cidStr.substring(6, 14)) < 18)
			return "未满18周岁不能开户";
		if(typeof(checkAge)!="undefined"){
			if(parseInt(checkAge)>0){
				if(calcAge(cidStr.substring(6, 14))>=parseInt(checkAge)){
					return "年满"+parseInt(checkAge)+"周岁不允许网上开户,请提示用户到营业部柜台办理";
				}
			}	
		}
		
		var ai = cidStr.substr(0, 17);

		var totalMulAiWi = 0;
		for (var i = 0; i < 17; i++) {
			totalMulAiWi = totalMulAiWi + parseInt(ai.charAt(i)) * parseInt(wi[i]);
		}
		var modValue = totalMulAiWi % 11;
		var strVerifyCode = valCodeArr[modValue];
		ai = ai + strVerifyCode;

		if (ai != cidStr) {
			return "身份证号码填写错误";//校验位有误
		}
	} catch(e) {
		return "身份证号码填写错误";
	}
	
	return "success";
	//return cidCity[parseInt(cidStr.substr(0,2))] + "," + sBirthday + "," + (cidStr.substr(16,1)%2 ? "男":"女");
}

//身份证地区编码
var cidCity = {
	11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",33:"浙江",34:"安徽",
	35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",
	53:"云南",54:"西藏",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"
};
var valCodeArr = [ "1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2" ];  
var wi = [ "7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7", "9", "10", "5", "8", "4", "2" ];  

/**
 * 计算周岁
 * @param dateText '19880101'
 */
function calcAge(dateText) {
	var bir = new Date(dateText.substring(0, 4), parseInt(dateText.substring(4, 6))-1, dateText.substring(6, 8));
	var cur = new Date();
	var ageDate = new Date(cur.getTime() - bir.getTime()-60*60*24*1000);
	return ageDate.getFullYear()-1970;
}

/**
 * 发证机关校验
 * @param sCIDCert
 */
function validateCIDCert(sCIDCert) {
	//发证机关规则(5-30个字符)
	var sLength = getLength(sCIDCert);
	if (sLength < 5) {
		return "请按照身份证填写";
	} else if (sLength > 30) {
		return "发证机关填写错误";
	} else {
		return "success";
	}
}

/**
 * 证件地址校验
 * @param sCIDAddr
 */
function validateCIDAddr(sCIDAddr) {
	//证件地址规则(10-150个字符)
	var sLength = getLength(sCIDAddr);
	if (sLength < 8) {
		return "证件地址错误，最少8个字";
	} else if (sLength > 40) {
		return "证件地址错误，最多40个字";
	} else {
		return "success";
	}
}

/**
 * 联系地址校验：详细地址
 * @param sAddress
 */	
function validateAddress(sAddress) {
	//联系地址规则
	var sLength = getLength(sAddress);
	if (sLength < 8) {
        return "请填写详细的联系地址，最少8个字";
    } else if (sLength > 40) {
        return "联系地址填写错误，最多40个字";
	} else {
		return "success";
	}
}

/**
 * 联系地址校验：详细地址
 * @param sAddress
 */	
function wlValidateAddress(sAddress) {
	//联系地址规则
	var sLength = getLength(sAddress);
	if (sLength < 8) {
        return "请填写详细的联系地址，最少8个字";
    } else if (sLength > 25) {
        return "联系地址填写错误，最多25个字";
	} else {
		return "success";
	}
}

/**
 * 地址过滤特殊字符
 * 
 * @param str
 * @returns
 */
function specialCharFilter(str) {
	// 过滤特殊字符，可包含符号'#','(',')'，其它符号及空格和换行符过滤
	
	/*var pattern = new RegExp(
			"[`~!@$%^&*=|{}':;'\\[\\].<>/\\\\?~<>«+＋－×＼《》！％@￥……&*（）——|{}【】‘；：”“'。、？\\s/\"/g]+",
			"g");*/
	var pattern = new RegExp("[\\s]+", "g");
	var newStr = str.replace(pattern, '');
	return newStr;
}

/**
 * start M201506290191 2015-0629 wangmy
 * 将字符串中全角数字替换为半角数字
 * 
 * @param str
 * @returns {String}
 */
function replaceSBCCaseNumber(str){
	var pattern = new RegExp("０", "g");
	var newStr = str.replace(pattern, '0');
	
	pattern = new RegExp("１", "g");
	newStr = newStr.replace(pattern, '1');
	
	pattern = new RegExp("２", "g");
	newStr = newStr.replace(pattern, '2');
	
	pattern = new RegExp("３", "g");
	newStr = newStr.replace(pattern, '3');
	
	pattern = new RegExp("４", "g");
	newStr = newStr.replace(pattern, '4');
	
	pattern = new RegExp("５", "g");
	newStr = newStr.replace(pattern, '5');
	
	pattern = new RegExp("６", "g");
	newStr = newStr.replace(pattern, '6');
	
	pattern = new RegExp("７", "g");
	newStr = newStr.replace(pattern, '7');
	
	pattern = new RegExp("８", "g");
	newStr = newStr.replace(pattern, '8');
	
	pattern = new RegExp("９", "g");
	newStr = newStr.replace(pattern, '9');
	return newStr;
}
// end M201506290191 2015-0629 wangmy

/**
 * 邮政编码校验
 * @param sPostCode
 */
function validatePostCode(sPostCode) {
	//邮政编码规则
	var regPostCode = /^[0-9]{6}(?![0-9])$/;
	if(!regPostCode.test(sPostCode)) {
		return "邮政编码填写错误";
	} else {
		return "success";
	}
}

/**
 * 固定电话校验
 * @param sTel
 */
function validateTel(sTel) {
	if(null == sTel || sTel == ""){
		return "success";
	}
	//固定电话规则
	var regTel = /^(\d{3,4}-)?\d{7,8}$/;
	if(!regTel.test(sTel)) {
		return "固定电话填写错误";
	} else {
		return "success";
	}
}

/**
 * 电子邮件校验
 * @param sEmail
 */
function validateEmail(sEmail) {
	if(null == sEmail || sEmail == ""){
		return "success";
	}
	//电子邮件规则
	var regEmail = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
	if(!regEmail.test(sEmail)) {
		return "电子邮件填写错误";
	} else {
		return "success";
	}
}

/**
 * 银行卡号校验
 * @param sEmail
 */
function validateBankNo(sBankNo) {
	//银行卡号规则
	var regBankNo = /^\d{9,}$/;
	if(!regBankNo.test(sBankNo)) {
		return "银行卡号填写错误";
	} else {
		return "success";
	}
}

/**
 * 时间格式校验
 * @param sEmail
 */
function validateDate(sDate) {
	var sdate = sDate.split("-");
	//时间格式规则
	var regDate = /^\d{4}.\d{2}.\d{2}$/;
	var strYear1 = $.trim(sdate[0]).substring(0, 4);// 年份  
	var strMonth1 = $.trim(sdate[0]).substring(5, 7);// 月份  
	var strDay1 = $.trim(sdate[0]).substring(8, 10);// 日  
	
	var strYear2 = '';// 年份  
	var strMonth2 = '';// 月份  
	var strDay2 = '';// 日 
	if( $.trim(sdate[1]) != "长期有效"){
		 strYear2 = $.trim(sdate[1]).substring(0, 4);// 年份  
		 strMonth2 = $.trim(sdate[1]).substring(5, 7);// 月份  
		 strDay2 = $.trim(sdate[1]).substring(8, 10);// 日 
	}
	if(regDate.test($.trim(sdate[0]))) {
		if(isNaN(strYear1)&&isNaN(strMonth1)&&isNaN(strDay1)){
			return "身份证有效期限格式不对";
		}
		if(parseInt(strYear1)%4==0&&parseInt(strMonth1)==2&&parseInt(strDay1)>29) {
			return "身份证有效期限格式不对";
		}else if(parseInt(strYear1)%4>0&&parseInt(strMonth1)==2&&parseInt(strDay1)>28){
			return "身份证有效期限格式不对";
		}else if((strMonth1==1||strMonth1==3||strMonth1==5||strMonth1==7||strMonth1==8||strMonth1==10||strMonth1==12)&&strDay1>31) {
			return "身份证有效期限格式不对";
		}else if((strMonth1==4||strMonth1==6||strMonth1==9||strMonth1==11)&&strDay1>30){
			return "身份证有效期限格式不对";
		}
	}else {
		return "身份证有效期限格式不对";
	}
	
	if ($.trim(sdate[1]) == "长期有效" ){
		return "success";
	}else if (regDate.test($.trim(sdate[1]))){
		if(isNaN(strYear2)&&isNaN(strMonth2)&&isNaN(strDay2)){
			return "身份证有效期限格式不对";
		}
		if(parseInt(strYear2)%4==0&&parseInt(strMonth2)==2&&parseInt(strDay2)>29) {
			return "身份证有效期限格式不对";
		}else if(parseInt(strYear2)%4>0&&parseInt(strMonth2)==2&&parseInt(strDay2)>28){
			return "身份证有效期限格式不对";
		}else if((strMonth2==1||strMonth2==3||strMonth2==5||strMonth2==7||strMonth2==8||strMonth2==10||strMonth2==12)&&strDay2>31) {
			return "身份证有效期限格式不对";
		}else if((strMonth2==4||strMonth2==6||strMonth2==9||strMonth2==11)&&strDay2>30){
			return "身份证有效期限格式不对";
		}
		return "success";
	}else {
		return "身份证有效期限格式不对";
	}
}

/**
 * 职业校验
 * @param sJob
 */
function validateJob(sJob) {
	//职业规则
	if (sJob == -1) {
		return "请选择职业";
	}
	return "success";
}

/**
 * 学历校验
 * @param sEdu
 */
function validateEdu(sEdu) {
	//学历规则
	if (sEdu >= 1){
		return "success";
	} else if (sEdu == -1) {
		return "请选择学历";
	} else {
		return "学历选择错误";
	}
}

/**
 * 验证反洗钱风险等级  M201503090005 20150424 wmy add
 * @param sAmlrisklevel
 */
function validateAmlrisklevel(sAmlrisklevel) {
	//反洗钱风险等级规则
	if (sAmlrisklevel >= 0){
		return "success";
	} else if (sAmlrisklevel < 0) {
		return "反洗钱风险等级选择错误";
	} else {
		return "请选择反洗钱风险等级";
	}
}

/**
 * 计算字符串长度
 * @param str
 * @return
 */
function getLength(str) {
	if(!str) return 0;
	//计算
	var tempStr = str.replace(/[^\x00-\xff]/g, '**').replace(/\s+/g, '*');
	var len = Math.ceil(tempStr.length/2);
	return len;
}
function validatePwd(field) {
		if (/^\d{6}$/.test(field.value)) {
			field.handleSuccess();
			$(field).data('localPass', true);
		} else {
			field.handleFail('密码为6位数字');
			$(field).data('localPass', false);
		}
}

function showTip(elem, content, type){
	TIP.show(elem,{
   		tipType:type,
   		tipText:content,
   		hasBtn:true
   	});
}

function showTipNoBtn(elem, content, type){
	TIP.show(elem,{
   		tipType:type,
   		tipText:content,
   		hasBtn:false
   	});
}

function validateTelAndPhone(sMobile){
	var regMobile=/^[0-9]*$/;
	if(!regMobile.test(sMobile)){
		return "号码填写错误";
	} else{
		return "success";
	}
}

