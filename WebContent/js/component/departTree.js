/**
 * 
 */

function departTree(container){
	webFXTreeConfig.rootIcon = $cntPath+"/js/component/xtree2b/images/folder.png";
    webFXTreeConfig.openRootIcon = $cntPath+"/js/component/xtree2b/images/openfolder.png";
    webFXTreeConfig.folderIcon = $cntPath+"/js/component/xtree2b/images/folder.png";
    webFXTreeConfig.openFolderIcon = $cntPath+"/js/component/xtree2b/images/openfolder.png";
    webFXTreeConfig.fileIcon=$cntPath+"/js/component/xtree2b/images/file.png";
    webFXTreeConfig.iIcon = $cntPath+"/js/component/xtree2b/images/I.png";
    webFXTreeConfig.lIcon = $cntPath+"/js/component/xtree2b/images/L.png";
    webFXTreeConfig.lMinusIcon = $cntPath+"/js/component/xtree2b/images/Lminus.png";
    webFXTreeConfig.lPlusIcon = $cntPath+"/js/component/xtree2b/images/Lplus.png";
    webFXTreeConfig.tIcon = $cntPath+"/js/component/xtree2b/images/T.png";
    webFXTreeConfig.tMinusIcon = $cntPath+"/js/component/xtree2b/images/Tminus.png";
    webFXTreeConfig.tPlusIcon = $cntPath+"/js/component/xtree2b/images/Tplus.png";
    webFXTreeConfig.blankIcon = $cntPath+"/js/component/xtree2b/images/blank.png";
    webFXTreeConfig.plusIcon = $cntPath+"/js/component/xtree2b/images/plus.png";
    webFXTreeConfig.minusIcon = $cntPath+"/js/component/xtree2b/images/minus.png";
    
	var tree = new WebFXTree('root node'); 
    tree.showRootNode = false; 
    tree.showRootLines = false; 
    var data = this.getData($cntPath+"/sysConfig.spr?method=queryCompanyData", "json");
    if(data && data.length>0){
    	var item = new WebFXLoadTreeItem(data[0].coName, $cntPath+"/sysConfig.spr?method=queryDepartmentData&coId="+data[0].coId+"&level0=1","javascript:void(0)");
//    	item.id = data[0].coId;
        tree.add(item);
        tree.appendTo(container);
    }
    
};
departTree.prototype.getData = function(url,dataType){
	var rtnData ;
	$.ajax({
		 type:"POST",
		 url:url,			 
		 data:"queryCondition="+JSON.stringify({}),
		 async:false,
		 dataType:"json",
		 success:function(data){
			 rtnData = data;
		 },
		 error:function(msg){
			 alert("error:"+msg);
		 }
	 });
	return rtnData;
}