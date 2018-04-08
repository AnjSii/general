// All material copyright ESRI, All Rights Reserved, unless otherwise specified.
// See https://js.arcgis.com/4.6/esri/copyright.txt for details.
//>>built
define("require exports ../support/utils ../../../core/promiseUtils ./support/utils dojo/_base/lang ../statistics/summaryStatistics ../../support/AuthoringInfo ../../support/AuthoringInfoVisualVariable".split(" "),function(t,l,h,f,g,m,n,p,q){function r(b){if(!(b&&b.layer&&(b.field||b.valueExpression||b.sqlExpression)))return f.reject(g.createError("opacity-visual-variable:missing-parameters","'layer' and 'field', 'valueExpression' or 'sqlExpression' parameters are required"));var a=m.mixin({},b);
b=[0,1];var k=h.createLayerAdapter(a.layer,b);return(a.layer=k)?k.load().then(function(){var b=h.getFieldsList({field:a.field,normalizationField:a.normalizationField,valueExpression:a.valueExpression});return(b=g.verifyBasicFieldValidity(k,b,"opacity-visual-variable:invalid-parameters"))?f.reject(b):a}):f.reject(g.createError("opacity-visual-variable:invalid-parameters","'layer' must be one of these types: "+h.getLayerTypeLabels(b).join(", ")))}Object.defineProperty(l,"__esModule",{value:!0});l.createVisualVariable=
function(b){return r(b).then(function(a){return(a.statistics?f.resolve(a.statistics):n({layer:a.layer,field:a.field,valueExpression:a.valueExpression,sqlExpression:a.sqlExpression,sqlWhere:a.sqlWhere,normalizationType:a.normalizationField?"field":void 0,normalizationField:a.normalizationField,minValue:a.minValue,maxValue:a.maxValue})).then(function(b){var d=a.layer,e=a.field,d=(d=e&&"function"!==typeof e?d.getField(e):null)&&"date"===d.type,c=g.createStopValues(b),c=(d=g.getDefaultDataRange(b,d,!0))||
[c[0],c[4]],e={type:"opacity",field:e,valueExpression:a.valueExpression,valueExpressionTitle:a.valueExpressionTitle,normalizationField:a.normalizationField,stops:[{value:c[0],opacity:.3},{value:c[1],opacity:.7}],legendOptions:a.legendOptions},c=new q({type:"opacity",minSliderValue:b.min,maxSliderValue:b.max}),c=new p({visualVariables:[c]});return f.resolve({visualVariable:e,statistics:b,defaultValuesUsed:!!d,authoringInfo:c})})})}});