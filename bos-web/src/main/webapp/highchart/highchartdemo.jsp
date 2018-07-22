<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/highcharts.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/exporting.js"></script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div id="container"></div>
<script type="text/javascript">
	Highcharts.chart('container', {
	chart: {
		plotBackgroundColor: null,
		plotBorderWidth: null,
		plotShadow: false,
		type: 'pie'
	},
	title: {
		text: 'iteason'
	},
	tooltip: {
		pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
	},
	plotOptions: {
		pie: {
			allowPointSelect: true,
			cursor: 'pointer',
			dataLabels: {
				enabled: true,
				format: '<b>{point.name}</b>: {point.percentage:.1f} %',
				style: {
					color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
				}
			}
		}
	},
	series: [{
		name: 'Brands',
		colorByPoint: true,
		data: [{
			name: 'Chrome',
			y: 10.00,
			sliced: true,
			selected: true
			}, {
			name: 'Internet Explorer',
			y: 11.84
			}, {
			name: 'Firefox',
			y: 10.85
			}, {
			name: 'Edge',
			y: 4.67
			}, {
			name: 'Safari',
			y: 4.18
			}, {
			name: 'Sogou Explorer',
			y: 1.64
			}, {
			name: 'Opera',
			y: 1.6
			}, {
			name: 'QQ',
			y: 1.2
			}, {
			name: 'Other',
			y: 2.61
			}]
	}]
});
	</script>
</body>
</html>