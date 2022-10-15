<#include "/admin/utils/ui.ftl"/>
<@layout>

<section class="content-header">
    <h1>词条管理</h1>
    <ol class="breadcrumb">
        <li><a href="${base}/admin">首页</a></li>
        <li class="active">词条管理</li>
    </ol>
</section>
<section class="content container-fluid">
    <div class="row">
        <div class="col-md-12">
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">词条列表</h3>
<#--                    <div class="box-tools">-->
<#--                        <a class="btn btn-default btn-sm" href="${base}/admin/post/view">新建</a>-->
<#--                        <a class="btn btn-default btn-sm" href="javascrit:;" data-action="batch_del">批量删除</a>-->
<#--                    </div>-->
                </div>
                <div class="box-body">
                    <form id="qForm" class="form-inline search-row">
                        <input type="hidden" name="pageNo" value="${page.number + 1}"/>
<#--                        <div class="form-group">-->
<#--                            <select class="form-control" name="channelId" data-select="${channelId}">-->
<#--                                <option value="0">查询所有分类</option>-->
<#--                                <#list channels as row>-->
<#--                                    <option value="${row.id}">${row.name}</option>-->
<#--                                </#list>-->
<#--                            </select>-->
<#--                        </div>-->
<#--                        <div class="form-group">-->
<#--                            <input type="text" name="title" class="form-control" value="${title}" placeholder="请输入标题关键字">-->
<#--                        </div>-->
<#--                        <button type="submit" class="btn btn-default">查询</button>-->
<#--                    </form>-->
                    <div class="table-responsive">
                        <table id="dataGrid" class="table table-striped table-bordered">
                            <thead>
                            <tr>
<#--                                <th width="50"><input type="checkbox" class="checkall"></th>-->
<#--                                <th width="80">#</th>-->
                                <th>词条标题</th>
                                <th width="120">分类</th>
                                <th width="120">作者</th>
                                <th width="120">发表日期</th>
<#--                                <th width="100">访问数</th>-->
<#--                                <th width="80">状态</th>-->
                                <th width="100">状态</th>
                                <th width="200">操作</th>
                            </tr>
                            </thead>
                            <tbody>

                                <#list items as row>
                                <tr>
<#--                                    <td id="id">-->
<#--                                        <input type="checkbox" name="id" value="${row.id}">-->
<#--                                    </td>-->
                                    <td id="title">
<#--                                        <a href="${base}/post/${row.id}" target="_blank">${row.title}</a>-->
                                        ${row.title}
                                    </td>

                                    <th>${row.channelId}</th>
                                    <td>${row.authorId}</td>
                                    <td>${row.created?string('yyyy-MM-dd')}</td>
<#--                                    <td><span class="label label-default">${row.views}</span></td>-->
                                    <td>
                                        <#if (row.status = 0)>
                                            <span class="label label-warning">待审核</span>
                                        </#if>
                                        <#if (row.status = 1)>
                                            <span class="label label-success">可编辑</span>
                                        </#if>
                                    </td>
                                    <td>
                                        <#if (row.status=0)>
                                        <a href="${base}/admin/post/audit?hid=${row.hid}" class="btn btn-xs btn-success">审核</a>
                                        </#if>
                                        <a href="${base}/admin/post/history?id=${row.id}" class="btn btn-xs btn-warning">日志</a>
                                        <a href="javascript:void(0);" class="btn btn-xs btn-danger" data-id="${row.hid}" rel="delete">删除</a>

                                    </td>
                                </tr>
                                </#list>
                            </tbody>
                        </table>
                    </div>
                    </form>
                </div>
                <div class="box-footer">
                    <@pager "list" page 5 />
                </div>
            </div>
        </div>
    </div>
</section>
<script type="text/javascript">
var J = jQuery;

function ajaxReload(json){
    if(json.code >= 0){
        if(json.message != null && json.message != ''){
			layer.msg(json.message, {icon: 1});
        }
        $('#qForm').submit();
    }else{
		layer.msg(json.message, {icon: 2});
    }
}

function doDelete(ids) {
	J.getJSON('${base}/admin/post/delete', J.param({'id': ids}, true), ajaxReload);
}

function doUpdateFeatured(id, featured) {
    J.getJSON('${base}/admin/post/featured', J.param({'id': id, 'featured': featured}, true), ajaxReload);
}

function doUpdateWeight(id, weight) {
    J.getJSON('${base}/admin/post/weight', J.param({'id': id, 'weight': weight}, true), ajaxReload);
}

$(function() {
    fetch(`http://localhost:9090/admin/post/log/latest`,{
        method:'GET',
        headers:{'Content-Type':'application/json'}}).then((res)=>{
        return res.text()
    }).then(res=>{
        var response = JSON.parse(res)
        // console.log(response)
        for(let row of response){
            console.log(row)
            console.log(row.channelId)
            // document.getElementById("id").value = 14
            document.getElementById("name").innerText= row.channelId
        }
    });
	// 删除
    $('#dataGrid a[rel="delete"]').bind('click', function(){
        var that = $(this);
		layer.confirm('确定删除此项吗?', {
            btn: ['确定','取消'], //按钮
            shade: false //不显示遮罩
        }, function(){
            fetch(`http://localhost:9090/admin/post/log/delete`,{
                method:'POST',
                body:JSON.stringify({
                        hid:that.attr('data-id')
                    }),
                headers:{'Content-Type':'application/json'}}).then((res)=>{
                // return res.text()
                console.log("ok")
                window.location.href = "http://localhost:9090/admin/post/list"
            // }).then(res=>{

            })
			// doDelete(that.attr('data-id'));
        }, function(){
        });
        return false;
    });

    // 推荐/加精
    $('#dataGrid a[rel="featured"]').bind('click', function(){
        var that = $(this);
        layer.confirm('确定推荐吗?', {
            btn: ['确定','取消'], //按钮
            shade: false //不显示遮罩
        }, function(){
            doUpdateFeatured(that.attr('data-id'), 1);
        }, function(){
        });
        return false;
    });

    // 撤销
    $('#dataGrid a[rel="unfeatured"]').bind('click', function(){
        var that = $(this);
        layer.confirm('确定撤销吗?', {
            btn: ['确定','取消'], //按钮
            shade: false //不显示遮罩
        }, function(){
            doUpdateFeatured(that.attr('data-id'), 0);
        }, function(){
        });
        return false;
    });

    // 推荐/加精
    $('#dataGrid a[rel="weight"]').bind('click', function(){
        var that = $(this);
        layer.confirm('确定置顶该项吗', {
            btn: ['确定','取消'], //按钮
            shade: false //不显示遮罩
        }, function(){
            doUpdateWeight(that.attr('data-id'), 1);
        }, function(){
        });
        return false;
    });

    // 撤销
    $('#dataGrid a[rel="unweight"]').bind('click', function(){
        var that = $(this);
        layer.confirm('确定撤销吗?', {
            btn: ['确定','取消'], //按钮
            shade: false //不显示遮罩
        }, function(){
            doUpdateWeight(that.attr('data-id'), 0);
        }, function(){
        });
        return false;
    })
    
    $('a[data-action="batch_del"]').click(function () {
		var check_length=$("input[type=checkbox][name=id]:checked").length;
		
		if (check_length == 0) {
			layer.msg("请至少选择一项", {icon: 2});
			return false;
		}
		
		var ids = [];
		$("input[type=checkbox][name=id]:checked").each(function(){
			ids.push($(this).val());
		});
		
		layer.confirm('确定删除此项吗?', {
            btn: ['确定','取消'], //按钮
            shade: false //不显示遮罩
        }, function(){
			doDelete(ids);
        }, function(){
        });
    });
})
</script>
</@layout>
