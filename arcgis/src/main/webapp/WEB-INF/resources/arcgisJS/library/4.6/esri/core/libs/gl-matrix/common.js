// All material copyright ESRI, All Rights Reserved, unless otherwise specified.
// See https://js.arcgis.com/4.6/esri/copyright.txt for details.
//>>built
define([],function(){var a={EPSILON:1E-6};a.ARRAY_TYPE="undefined"!==typeof Float32Array?Float32Array:Array;a.RANDOM=Math.random;a.ENABLE_SIMD=!1;a.SIMD_AVAILABLE=a.ARRAY_TYPE===Float32Array&&"SIMD"in this;a.USE_SIMD=a.ENABLE_SIMD&&a.SIMD_AVAILABLE;a.setMatrixArrayType=function(b){a.ARRAY_TYPE=b};var d=Math.PI/180;a.toRadian=function(a){return a*d};a.equals=function(b,c){return Math.abs(b-c)<=a.EPSILON*Math.max(1,Math.abs(b),Math.abs(c))};return a});