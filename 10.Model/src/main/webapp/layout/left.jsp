<%@ page contentType="text/html; charset=euc-kr" %>
<%@ page import="com.model2.mvc.service.domain.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<title>Model2 MVC Shop</title>
<link href="/css/left.css" rel="stylesheet" type="text/css">
<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
<script type="text/javascript">
    // JavaScript �Լ� �̸� ����
   function history(){
			popWin = window.open("/history.jsp",
														"popWin",
														"left=300, top=200, width=300, height=200, marginwidth=0, marginheight=0, scrollbars=no, scrolling=no, menubar=no, resizable=no");
		}

    $(function(){
    	
    	$( ".Depth03:contains('����������ȸ')" ).on("click" , function() {
			
    		//Debug..
			alert(  $( ".Depth03:contains('����������ȸ')" ).html() );
			$(window.parent.frames["rightFrame"].document.location).attr("href","/user/getUser?userId=${user.userId}");
		});
    	
    	/* frames ��ü�� ����ϸ� �ش� â�� �ε�� ��� �����ӿ� ������ �� �����Ƿ�, window.parent�� ����� �ʿ䰡 ����
		window.parent�� ���� â�� �θ� â�� ��Ÿ����, frames["rightFrame"]�� ���� â�� �ε�� ��� ������ �� rightFrame�̶�� �������� ��Ÿ��.  */

        $(".Depth03:contains('ȸ��������ȸ')").on("click", function(){
        	
        	alert($(".Depth03:contains('ȸ��������ȸ')").html());
            window.parent.frames["rightFrame"].location.href = "/user/listUser";
        });

        $(".Depth03:contains('�ǸŻ�ǰ���')").on("click", function(){
        	
        	alert($(".Depth03:contains('�ǸŻ�ǰ���')").html());
            window.parent.frames["rightFrame"].location.href = "../product/addProductView.jsp";
        });

        $(".Depth03:contains('�ǸŻ�ǰ����')").on("click", function(){
        	
        	alert($(".Depth03:contains('�ǸŻ�ǰ����')").html());
            window.parent.frames["rightFrame"].location.href = "/product/listProduct?menu=manage";
        });

        $(".Depth03:contains('�� ǰ �� ��')").on("click", function(){
        	
        	alert($(".Depth03:contains('�� ǰ �� ��')").html());
            window.parent.frames["rightFrame"].location.href = "/product/listProduct?menu=search";
        });

        $(".Depth03:contains('�����̷���ȸ')").on("click", function(){
        	
        	alert($(".Depth03:contains('�����̷���ȸ')").html());
            window.parent.frames["rightFrame"].location.href = "/purchase/listPurchase";
        });

        $(".Depth03:contains('�ֱ� �� ��ǰ')").on("click", function(){
        	
        	alert($(".Depth03:contains('�ֱ� �� ��ǰ')").html());
        	history();
        	
        });
    });
</script>
</head>

<body background="/images/left/imgLeftBg.gif" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<table width="159" border="0" cellspacing="0" cellpadding="0">
<tr>
    <td valign="top">
        <table border="0" cellspacing="0" cellpadding="0" width="159">
            <tr>
                <c:if test="${ !empty user }">
                    <td class="Depth03">     
                        ����������ȸ
                    </td>
                </c:if>
            </tr>
            
            <c:if test="${user.role == 'admin'}">
                <tr>
                    <td class="Depth03">
                       ȸ��������ȸ
                    </td>
                </tr>
            </c:if>
            
            <tr>
                <td class="DepthEnd">&nbsp;</td>
            </tr>
        </table>
    </td>
</tr>

<c:if test="${user.role == 'admin'}">
    <tr>
        <td valign="top">
            <table border="0" cellspacing="0" cellpadding="0" width="159">
                <tr>
                    <td class="Depth03">
                      �ǸŻ�ǰ���
                    </td>
                </tr>
                <tr>
                    <td class="Depth03">
                       �ǸŻ�ǰ����
                    </td>
                </tr>
                <tr>
                    <td class="DepthEnd">&nbsp;</td>
                </tr>
            </table>
        </td>
    </tr>
</c:if>
<tr>
    <td valign="top">
        <table border="0" cellspacing="0" cellpadding="0" width="159">
            <tr>
                <td class="Depth03">
                    �� ǰ �� ��
                </td>
            </tr>
            <c:if test="${!empty user && user.role == 'user'}">
                <tr>
                    <td class="Depth03">
                       �����̷���ȸ
                    </td>
                </tr>
            </c:if>
            <tr>
                <td class="DepthEnd">&nbsp;</td>
            </tr>
            <tr>
                <td class="Depth03">
                    �ֱ� �� ��ǰ
                </td>
            </tr>
        </table>
    </td>
</tr>
</table>
</body>
</html>
