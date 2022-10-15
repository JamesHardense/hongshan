<!-- Login dialog BEGIN -->
<div id="login_alert" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document" style="width: 400px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">请登录</h4>
            </div>
            <div class="modal-body">
                <form method="POST" action="${base}/login" accept-charset="UTF-8">
                    <div class="form-group">
                        <label class="control-label" for="username">账号</label>
                        <input class="form-control" id="ajax_login_username" name="username" type="text" required>
                    </div>
                    <div class="form-group">
                        <label class="control-label" for="password">密码</label>
                        <input class="form-control" id="ajax_login_password" name="password" type="password" required>
                    </div>
                    <div class="form-group">
                        <button id="ajax_login_submit" class="btn btn-primary btn-block btn-sm" type="button">
                            登录 Use it
                        </button>
                    </div>
                    <div class="form-group">
                        <div id="ajax_login_message" class="text-danger"></div>
                    </div>
                    <@controls name="register">
                        <fieldset class="form-group">
			    <#if site.hasValue("weibo_client_id")>
                            <a class="btn btn-default btn-block" href="${base}/oauth/callback/call_weibo">
                                <i class="fa fa-weibo"></i> 微博帐号登录
                            </a>
                            </#if>
                            <#if site.hasValue("qq_app_id")>
                            <a class="btn btn-default btn-block" href="${base}/oauth/callback/call_qq">
                                <i class="fa fa-qq"></i> QQ帐号登录
                            </a>
                            </#if>
                            <#if site.hasValue("github_client_id")>
                            <a class="btn btn-default btn-block" href="${base}/oauth/callback/call_github">
                                <i class="fa fa-github"></i> Github帐号登录
                            </a>
                            </#if>
                        </fieldset>
                    </@controls>
                </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<!-- Login dialog END -->

<!--[if lt IE 9]>
<div class="alert alert-danger alert-dismissible fade in" role="alert" style="margin-bottom:0">
	<button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
	<strong>您正在使用低版本浏览器，</strong> 在本页面的显示效果可能有差异。
	建议您升级到
	<a href="http://www.google.cn/intl/zh-CN/chrome/" target="_blank">Chrome</a>
	或以下浏览器：
	<a href="www.mozilla.org/en-US/firefox/‎" target="_blank">Firefox</a> /
	<a href="http://www.apple.com.cn/safari/" target="_blank">Safari</a> /
	<a href="http://www.opera.com/" target="_blank">Opera</a> /
	<a href="http://windows.microsoft.com/en-us/internet-explorer/download-ie" target="_blank">Internet Explorer 9+</a>
</div>
<![endif]-->

<!-- Fixed navbar -->
<header class="site-header headroom">
    <div class="container">
        <nav class="navbar" role="navigation">
            <div class="navbar-header">
                <button class="navbar-toggle" type="button" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span>
                </button>
                <a  href="${base}/">
                    <img src="<@resource src=options['site_logo']/>" height="65" width="160"/>
                </a>
            </div>


            <div class="collapse navbar-collapse" style="margin:auto;display:flex">
                <ul class="nav navbar-nav" style="position:relative;">
					<#if profile??>
						<li data="user" style="margin-top: 8px;">
							<a href="${base}/users/${profile.id}" nav="user">我的主页</a>
						</li>
					</#if>
					<#list channels as row>
						<li style="margin-top: 8px;">
							<a href="${base}/channel/${row.id}" nav="${row.name}">${row.name}</a>
						</li>
					</#list>
                    <button id="btn" style="margin-left: 38px;margin-top: 15px;" type="button" class="btn btn-primary">更多分类</button>

                    <#--下拉框例子-->
<#--                    <div class="dropdown" style="position: absolute;left: 488px;top: 14px;">-->
<#--                        <button type="button" class="btn dropdown-toggle" id="dropdownMenu1" data-toggle="dropdown">更多-->
<#--                            <span class="caret"></span>-->
<#--                        </button>-->
<#--                        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">-->
<#--                    <#list last as list>-->
<#--                                <li >-->
<#--                                    <a  href="#">${list.name}</a>-->
<#--                                </li>-->
<#--                            </#list>-->

<#--                        </ul>-->
<#--                    </div>-->

<#--                        <li>-->
<#--                            <a style="color: #000000">-->
<#--                                社会&nbsp;&nbsp;-->
<#--                                <select>-->
<#--                                    <option><a href="${base}/channel/1">学校</a></option>-->
<#--                                    <option><a href="${base}/channel/2">企业</a></option>-->
<#--                                    <option><a href="${base}/channel/3">医院</a></option>-->
<#--                                    <option><a href="${base}/channel/4">科研机构</a></option>-->
<#--                                </select>-->
<#--                            </a>-->

<#--                        </li>-->
<#--                        <li>-->
<#--                            <a style="color: #000000">-->
<#--                                科技&nbsp;&nbsp;-->
<#--                                <select>-->
<#--                                    <option><a href="${base}/channel/5">软件</a></option>-->
<#--                                    <option><a href="${base}/channel/6">网站</a></option>-->
<#--                                    <option><a href="${base}/channel/7">电子产品</a></option>-->
<#--                                    <option><a href="${base}/channel/8">药品</a></option>-->
<#--                                </select>-->
<#--                            </a>-->
<#--                        </li>-->
<#--                        <li>-->
<#--                            <a style="color: #000000">-->
<#--                                人物&nbsp;&nbsp;-->
<#--                                <select>-->
<#--                                    <option><a href="${base}/channel/9">企业人物</a></option>-->
<#--                                    <option><a href="${base}/channel/10">体育人物</a></option>-->
<#--                                    <option><a href="${base}/channel/11">科学人物</a></option>-->
<#--                                    <option><a href="${base}/channel/12">文化人物</a></option>-->
<#--                                </select>-->
<#--                            </a>-->
<#--                        </li>-->
                </ul>
                <ul class="navbar-button list-inline" id="header_user" style="margin-top: 8px;">
                    <li view="search" class="hidden-xs hidden-sm">
                        <form method="GET" action="${base}/search" accept-charset="UTF-8" class="navbar-form navbar-left">
                            <div class="form-group">
                                <input class="form-control search-input mac-style" placeholder="搜索" name="kw" type="text"  value="${kw}">
                                <button class="search-btn" type="submit"><i class="fa fa-search"></i></button>
                            </div>
                        </form>
                    </li>

				<#if profile??>
                    <@controls name="post">
                        <li style="margin-top: 3px;">
                            <a href="${base}/post/editing" class="plus"><i class="icon icon-note"></i> 写词条</a>
                        </li>
                    </@controls>
                    <li class="dropdown">
                        <a href="#" class="user dropdown-toggle" data-toggle="dropdown">
                            <img class="img-circle" src="<@resource src=profile.avatar + '?t=' + .now?time />">
                            <span>${profile.name}</span>
                        </a>
                        <ul class="dropdown-menu" role="menu">
                            <li>
                                <a href="${base}/users/${profile.id}">我的主页</a>
                            </li>
                            <li>
                                <a href="${base}/settings/profile">编辑资料</a>
                            </li>
                            <@shiro.hasPermission name="admin">
                                <li><a href="${base}/admin">后台管理</a></li>
                            </@shiro.hasPermission>
                            <li><a href="${base}/logout">退出</a></li>
                        </ul>
                    </li>
				<#else>
                    <li><a href="${base}/login" class="btn btn-default btn-sm signup">登录</a></li>
<#--                    <@controls name="register">-->
                    <li><a href="${base}/register" class="btn btn-primary btn-sm signup">注册</a></li>
<#--                    </@controls>-->
				</#if>

                </ul>
            </div>
        </nav>
    </div>
    <div style="position: relative;display: none;" id="channelShow">
        <div style="position: absolute;width: 596px;height: 68px;background-color: #FFFFFF;left: 277px">
            <#list last as list>
                <a style="color: #383838;font-size: 14px;top:10px;margin-left: 30px;" href="${base}/channel/${list.id}">${list.name}</a>
            </#list>
        </div>
    </div>
</header>

<script type="text/javascript">
$(function () {
	$('a[nav]').each(function(){  
        $this = $(this);
        if($this[0].href == String(window.location)){  
            $this.closest('li').addClass("active");  
        }  
    });
    // $("#myselect").change(function(){
    //     var opt=$("#myselect").val();
    // ...
    // });
    // document.getElementById("btn").onclick = function (){
    //     document.getElementById("channelShow").style.display = "block"
    // }
    document.getElementById("btn").onclick = function (){
        var div = document.getElementById("channelShow");
        if(div.style.display == ""){
            div.style.display = "none";
        }else{
            div.style.display = "";
        }
    }
});
</script>
<!-- Header END -->
