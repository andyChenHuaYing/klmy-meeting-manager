/*
*author gu.xiaogang
*date    20130930
*/
/*
*$cntPath
*上下文根
*/
$cntPath=(function(){
	var path = document.location.pathname;
	var index = path.substr(1).indexOf("/");
	return path.substr(0,index+1);
//	return '/';
})();

/**
 * 返回此字符串去除前后所有空格后的字符串。
 */
String.prototype.trim = function()
{
    return this.trimRight().trimLeft();
};

/*
 * 返回此字符串去除开始位置所有空格后的字符串。
 */
String.prototype.trimLeft = function()
{
    return this.replace(/^\s*/, "");
};

/**
 * 返回此字符串去除后面所有空格后的字符串。
 */
String.prototype.trimRight = function()
{
    return this.replace(/\s*$/, "");
};
/**
 * 返回此字符串是否以指定的字符串开头。
 * @param str  指定的字符串
 */
String.prototype.startWith = function(str)
{
    if(str == null){
       return false;
    }else{
        return this.substring(0, str.length) == str;
    }
};

/**
 * 返回此字符串是否以指定的字符串结尾。
 * @param str 指定的字符串
 */
String.prototype.endWith = function(str)
{
    if(str == null){
        return false;
    }else{
        return this.substring(this.length - str.length) == str;
    }
};

//SHA1
/////////////////////////////////////////////////////////////////////////////////////////////
/*
* A JavaScript implementation of the Secure Hash Algorithm, SHA-1, as defined
* in FIPS PUB 180-1
* Version 2.1a Copyright Paul Johnston 2000 - 2002.
* Other contributors: Greg Holt, Andrew Kepert, Ydnar, Lostinet
* Distributed under the BSD License
* See http://pajhome.org.uk/crypt/md5 for details.
*/

/*
* Configurable variables. You may need to tweak these to be compatible with
* the server-side, but the defaults work in most cases.
*/
var hexcase = 0;
/* hex output format. 0 - lowercase; 1 - uppercase        */
var b64pad = "";
/* base-64 pad character. "=" for strict RFC compliance   */
var chrsz = 8;
/* bits per input character. 8 - ASCII; 16 - Unicode      */

/*
 * These are the functions you'll usually want to call
 * They take string arguments and return either hex or base-64 encoded strings
 */
function hex_sha1(s) {
    return binb2hex(core_sha1(str2binb(s), s.length * chrsz));
}
function b64_sha1(s) {
    return binb2b64(core_sha1(str2binb(s), s.length * chrsz));
}
function str_sha1(s) {
    return binb2str(core_sha1(str2binb(s), s.length * chrsz));
}
function hex_hmac_sha1(key, data) {
    return binb2hex(core_hmac_sha1(key, data));
}
function b64_hmac_sha1(key, data) {
    return binb2b64(core_hmac_sha1(key, data));
}
function str_hmac_sha1(key, data) {
    return binb2str(core_hmac_sha1(key, data));
}

/*
 * Perform a simple self-test to see if the VM is working
 */
function sha1_vm_test()
{
    return hex_sha1("abc") == "a9993e364706816aba3e25717850c26c9cd0d89d";
}

/*
 * Calculate the SHA-1 of an array of big-endian words, and a bit length
 */
function core_sha1(x, len)
{
    /* append padding */
    x[len >> 5] |= 0x80 << (24 - len % 32);
    x[((len + 64 >> 9) << 4) + 15] = len;

    var w = Array(80);
    var a = 1732584193;
    var b = -271733879;
    var c = -1732584194;
    var d = 271733878;
    var e = -1009589776;

    for (var i = 0; i < x.length; i += 16)
    {
        var olda = a;
        var oldb = b;
        var oldc = c;
        var oldd = d;
        var olde = e;

        for (var j = 0; j < 80; j++)
        {
            if (j < 16) w[j] = x[i + j];
            else w[j] = rol(w[j - 3] ^ w[j - 8] ^ w[j - 14] ^ w[j - 16], 1);
            var t = safe_add(safe_add(rol(a, 5), sha1_ft(j, b, c, d)),
                    safe_add(safe_add(e, w[j]), sha1_kt(j)));
            e = d;
            d = c;
            c = rol(b, 30);
            b = a;
            a = t;
        }

        a = safe_add(a, olda);
        b = safe_add(b, oldb);
        c = safe_add(c, oldc);
        d = safe_add(d, oldd);
        e = safe_add(e, olde);
    }
    return Array(a, b, c, d, e);

}

/*
 * Perform the appropriate triplet combination function for the current
 * iteration
 */
function sha1_ft(t, b, c, d)
{
    if (t < 20) return (b & c) | ((~b) & d);
    if (t < 40) return b ^ c ^ d;
    if (t < 60) return (b & c) | (b & d) | (c & d);
    return b ^ c ^ d;
}

/*
 * Determine the appropriate additive constant for the current iteration
 */
function sha1_kt(t)
{
    return (t < 20) ? 1518500249 : (t < 40) ? 1859775393 :
                                   (t < 60) ? -1894007588 : -899497514;
}

/*
 * Calculate the HMAC-SHA1 of a key and some data
 */
function core_hmac_sha1(key, data)
{
    var bkey = str2binb(key);
    if (bkey.length > 16) bkey = core_sha1(bkey, key.length * chrsz);

    var ipad = Array(16), opad = Array(16);
    for (var i = 0; i < 16; i++)
    {
        ipad[i] = bkey[i] ^ 0x36363636;
        opad[i] = bkey[i] ^ 0x5C5C5C5C;
    }

    var hash = core_sha1(ipad.concat(str2binb(data)), 512 + data.length * chrsz);
    return core_sha1(opad.concat(hash), 512 + 160);
}

/*
 * Add integers, wrapping at 2^32. This uses 16-bit operations internally
 * to work around bugs in some JS interpreters.
 */
function safe_add(x, y)
{
    var lsw = (x & 0xFFFF) + (y & 0xFFFF);
    var msw = (x >> 16) + (y >> 16) + (lsw >> 16);
    return (msw << 16) | (lsw & 0xFFFF);
}

/*
 * Bitwise rotate a 32-bit number to the left.
 */
function rol(num, cnt)
{
    return (num << cnt) | (num >>> (32 - cnt));
}

/*
 * Convert an 8-bit or 16-bit string to an array of big-endian words
 * In 8-bit function, characters >255 have their hi-byte silently ignored.
 */
function str2binb(str)
{
    var bin = Array();
    var mask = (1 << chrsz) - 1;
    for (var i = 0; i < str.length * chrsz; i += chrsz)
        bin[i >> 5] |= (str.charCodeAt(i / chrsz) & mask) << (32 - chrsz - i % 32);
    return bin;
}

/*
 * Convert an array of big-endian words to a string
 */
function binb2str(bin)
{
    var str = "";
    var mask = (1 << chrsz) - 1;
    for (var i = 0; i < bin.length * 32; i += chrsz)
        str += String.fromCharCode((bin[i >> 5] >>> (32 - chrsz - i % 32)) & mask);
    return str;
}

/*
 * Convert an array of big-endian words to a hex string.
 */
function binb2hex(binarray)
{
    var hex_tab = hexcase ? "0123456789ABCDEF" : "0123456789abcdef";
    var str = "";
    for (var i = 0; i < binarray.length * 4; i++)
    {
        str += hex_tab.charAt((binarray[i >> 2] >> ((3 - i % 4) * 8 + 4)) & 0xF) +
               hex_tab.charAt((binarray[i >> 2] >> ((3 - i % 4) * 8  )) & 0xF);
    }
    return str;
}

/*
 * Convert an array of big-endian words to a base-64 string
 */
function binb2b64(binarray)
{
    var tab = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
    var str = "";
    for (var i = 0; i < binarray.length * 4; i += 3)
    {
        var triplet = (((binarray[i >> 2] >> 8 * (3 - i % 4)) & 0xFF) << 16)
                | (((binarray[i + 1 >> 2] >> 8 * (3 - (i + 1) % 4)) & 0xFF) << 8 )
                | ((binarray[i + 2 >> 2] >> 8 * (3 - (i + 2) % 4)) & 0xFF);
        for (var j = 0; j < 4; j++)
        {
            if (i * 8 + j * 6 > binarray.length * 32) str += b64pad;
            else str += tab.charAt((triplet >> 6 * (3 - j)) & 0x3F);
        }
    }
    return str;
}

/*
 * Util类实现部分
 */
function JsUtil(){
	if(typeof JsUtil._init == 'undefined'){
		
		var jsUtilStat = JsUtil.prototype;
		jsUtilStat.test = function(){
			return "testJsUtil"; 
		};
		jsUtilStat.getCurrTime = function(){
			return '&dt=' + new Date().getTime();
		};
		
		jsUtilStat.roleMap = {
							  chartExport:10000000000061,
							  chartPrint:10000000000062	
							 };
		
		/*
		 * 将字符串转成XML对象
		 */
		jsUtilStat.strToXml = function (str){
			var xml;
			var browserName = navigator.appName;
			if(browserName=="Microsoft Internet Explorer"){
				xml = new ActiveXObject("Microsoft.XMLDOM");
				xml.async = false;
				xml.load(str);
			}else{
				xml = new DOMParser().parseFromString(str,'text/xml');
			}				
			return xml;
		};
		
		jsUtilStat.$url=function(urlPath){
			var tmpUrlPath = urlPath.trim();
			if(tmpUrlPath.toLowerCase().match("^[a-z][a-z0-9]*://")||tmpUrlPath.startWith("/")){
				return urlPath;
			}
			var currentPath = location.pathname;
		    var i = currentPath.lastIndexOf("/", currentPath.length - 1);
		    if (i >= 0)
		    {
		        currentPath = currentPath.substring(0, i);
		    }
		    try
		    {
		        if (tmpUrlPath.startWith("~"))
		        {
		            var urlPath = $cntPath + tmpUrlPath.substring(1);
		            return urlPath;
		        }
		        else if (tmpUrlPath.startWith("../"))
		        {
		            var path = currentPath;
		            while (tmpUrlPath.startWith("../"))
		            {
		                i = path.lastIndexOf('/', path.length - 1);
		                if (i != -1)
		                {
		                    path = path.substring(0, i);
		                }
		                else
		                {
		                    throw new Error("“" + urlPath + "”路径不存在。");
		                }
		                tmpUrlPath = tmpUrlPath.substring(3);
		            }
		            if (!path.endWith("/"))
		            {
		                path += "/";
		            }
		            return path + tmpUrlPath;
		        }
		        else
		        {
		            return currentPath + "/" + tmpUrlPath;
		        }
		    }
		    catch (e)
		    {
		        throw new Error("“" + urlPath + "”路径不存在或不合法。");
		    }
		    
		};
		
		jsUtilStat.include = function(fileName){
			//fileName = this.$url(fileName);   
		    var fileId = "f_" + hex_sha1(fileName);
		    if (fileName.endWith(".js")) {
		        if (document.getElementById(fileId) == null) {
		            document.write("<script id='" + fileId + "' type='text/javascript'  src='" + fileName + "'></" + "script>");
		        }
		    }
		    else if (fileName.endWith(".css")) {
		        if (!document.getElementById(fileId))
		        {
		            document.write("<link id='" + fileId +"' rel='stylesheet' type='text/css' href='"+ fileName +"'>");
		        }
		    }
		};
		
		jsUtilStat.importFile = function(fileNames){
			if(fileNames.indexOf("jquery")!=-1){
				this.include($cntPath+"/js/jquery/jquery-1.9.1.js");
			}
			
			if(fileNames.indexOf("easyui")!=-1){
				this.include($cntPath+"/js/jquery-easyui-1.4/themes/default/easyui.css");
				this.include($cntPath+"/js/jquery-easyui-1.4/themes/icon.css");
				this.include($cntPath+"/js/jquery-easyui-1.4/jquery.easyui.min.js");
				this.include($cntPath+"/js/jquery-easyui-1.4/locale/easyui-lang-zh_CN.js");
			}
			if(fileNames.indexOf("ichart")!=-1){
				this.include($cntPath+"/js/ichart/ichart.1.2.1.min.js");
			}
		};
		
		//数组反转
		jsUtilStat.reverseArray = function(objArr){
			var length = objArr.length;
			for(var i=0 ; i <= parseInt((length-1)/2);i++){
				var tmpObj = objArr[i];
				objArr[i] = objArr[length-1-i];
				objArr[length-1-i] = tmpObj;
			}
			return objArr;
		};
		
		/*
		 * 获取数组中的平均值
		 */
		jsUtilStat.getAvgValue = function(arr){
			var sum = 0;
			for(var i = 0; i< arr.length;i++){
				sum += arr[i];
			}
			return sum/arr.length;
		};
		/*
		 * 获取数组中的最大值、最小值
		 * 通过冒泡排序算法
		 */
		jsUtilStat.getMaxMinValue = function(arr){
			var tmpValue=0;
			var switchFlag;
			var result = new Object();
			for(var i = 0;i<arr.length-1;i++){
				switchFlag = false;
				for(var j=0;j<arr.length-i-1;j++){
					if(arr[j]>arr[j+1]){
						tmpValue=arr[j];
						arr[j]=arr[j+1];
						arr[j+1]=tmpValue;
						switchFlag = true;
					}
				}
				if(!switchFlag){
					break;
				}
			}
			
			if(arr.length>0){
				result.max = arr[arr.length-1];
				result.min = arr[0];	
				result.mid = arr[parseInt((arr.length-1)/2)];
			}
			return result;
		};
		
		jsUtilStat.sortArr = function(arr,attr,isOrderByAttr){//isOrderByAttr是否按照属性来排序
			var tmpValue;
			var switchFlag;
			for(var i = 0;i<arr.length-1;i++){
				switchFlag = false;
				for(var j=0;j<arr.length-i-1;j++){
					if(!isOrderByAttr){
						if(arr[j]>arr[j+1]){
							tmpValue=arr[j];
							arr[j]=arr[j+1];
							arr[j+1]=tmpValue;
							switchFlag = true;
						}
					}else{
						if(arr[j][attr]>arr[j+1][attr]){
							tmpValue=arr[j];
							arr[j]=arr[j+1];
							arr[j+1]=tmpValue;
							switchFlag = true;
						}
					}
				}
				if(!switchFlag){
					break;
				}
			}
			return arr;
		};
		
		
		
		/*
		 * 去除数组重复元素
		 */
		jsUtilStat.delArrRepeat = function(arr){
			var newArray=[];
		    var provisionalTable = {};
		    for (var i = 0, item; (item= arr[i]) != null; i++) {
		    	console.log(item.toString());
		        if (!provisionalTable[item.toString()]) {
		            newArray.push(item);
		            provisionalTable[item.toString()] = true;
		        }
		    }
		    provisionalTable = null; 
		    return newArray;
		};
				
		/*
		 * @description 返回浏览器类型 
		 * @return 1:'IE',2:'Firefox',3:'chrome',4:'opera',5:'safari'
		 * 
		 */
		jsUtilStat.getBrower = function(){
			if(window.ActiveXObject){
				return 1;
			}else if(document.getBoxObjectFor){
				return 2;
			}else if(window.MessageEvent && !document.getBoxObjectFor){
				return 3;
			}else if(window.opera){
				return 4;
			}else if(window.openDatabase){
				return 5;
			}
		};		
		jsUtilStat.hasAuthority = function(roleId){
			var flag = false;
			$.ajax({
				type:"POST",
				url:$cntPath+"/role.spr?method=hasAuthority&dt="+new Date().getTime(),
				data:"roleId="+roleId,
				dataType:"text",
				async:false,
				success:function(data){
					if($.trim(data)=='true')
						flag = true;
				},
				error:function(msg){
					alert("error"+msg);
				}
			});
			return flag;
		};
		jsUtilStat.chartExport = function(chart,title){
			if(chart==null) return;
			var formHtml = '<form action="'+$cntPath+'/chartExport.spr?method=export" id="form" method="post"><input type="hidden" name="title" id="title">'+
    		'<input type="hidden" name="base64Str" id="base64Str"></form>';
			$("body").append(formHtml);
			if(title!=null && title!=""){
				$("#title").val(title);
			}
			$("#base64Str").val(chart.toDataURL());
			$("#form")[0].submit();
			$("#form").remove();
		};
		jsUtilStat.chartPrint = function(chart){
			var newstr = '<div style="width:100%;height:100%;"><img src="'+chart.toDataURL()+'" style="margin: 0px auto;display: block;padding-top:50px;"></div>';
	        printWindow = window.open();
	      　              //printWindow.document.write(newstr);
	        printWindow.document.body.innerHTML=newstr;
	      　              printWindow.focus();
	      　　      	printWindow.print();
	      　　      	return false;
		};
	}
	JsUtil._init = true;	
};
/*
 * 注册命名空间 $J
 * 
 */
(function(window){
	if(typeof window.$J == 'undefined'){
		window.$J = new JsUtil();
	}	
})(window);