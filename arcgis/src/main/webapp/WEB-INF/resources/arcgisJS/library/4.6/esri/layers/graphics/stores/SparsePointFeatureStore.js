// All material copyright ESRI, All Rights Reserved, unless otherwise specified.
// See https://js.arcgis.com/4.6/esri/copyright.txt for details.
//>>built
define("require exports ../../../core/Error ../../../core/LRUMap ../../../core/Logger ../../../core/promiseUtils ../../../core/sql/WhereClause ../../../core/libs/rbush/rbush ../../../geometry ../../../geometry/support/webMercatorUtils ../../../geometry/support/normalizeUtils".split(" "),function(z,A,p,r,t,k,u,v,w,x,y){t.getLogger("esri.layers.graphics.stores.SparseFeatureStore").level="warn";var q=new r(25);return function(){function d(a){var c=this;this._attributesMap=new Map;this._fieldsMap=new Map;
this.objectIdField=a.objectIdField;this.spatialReference=a.spatialReference;this._numFeatures=a.numFeatures;this._readCoordinates=a.readCoordinates;this._readFeatureAttributes=a.readFeatureAttributes;a.fields.forEach(function(a){c._fieldsMap.set(a.name,a);c._fieldsMap.set(a.name.toLowerCase(),a)})}d.prototype.destroy=function(){this._spatialIndex.clear();this._spatialIndex=null};d.prototype.execute=function(a){var c=this;return this._normalizeQueryGeometry(a).then(function(a){return k.all([c._checkQuerySupport(a),
c._buildSpatialIndex()])}).then(function(a){var b=a[0];a=a[1];var e=c._getWhereFilter(b),g=c._getCreateFeature(b);if(b.geometry){for(var m=[],f=0,b=c._getQueryBoundList(b.geometry);f<b.length;f++)for(var l=b[f],h=0,l=a.search({minX:l[0],minY:l[1],maxX:l[2],maxY:l[3]}).filter(e);h<l.length;h++)m.push(g(l[h]));return m}return a.all().filter(e).map(g)}).then(function(b){return c._createQueryResponse(a,b)})};d.prototype.executeForCount=function(a){var c=this;return this._normalizeQueryGeometry(a).then(function(a){return k.all([c._checkQuerySupport(a),
c._buildSpatialIndex()])}).then(function(a){var b=a[0];a=a[1];var e=c._getWhereFilter(b);if(b.geometry){for(var g=0,m=0,b=c._getQueryBoundList(b.geometry);m<b.length;m++)var f=b[m],f=a.search({minX:f[0],minY:f[1],maxX:f[2],maxY:f[3]}).filter(e),g=g+f.length;return g}return a.all().filter(e).length})};d.prototype.executeForExtent=function(a){var c=this;return this._normalizeQueryGeometry(a).then(function(a){return k.all([c._checkQuerySupport(a),c._buildSpatialIndex()])}).then(function(a){var b=a[0],
e=a[1],g=c._getWhereFilter(b);a={xmin:Number.POSITIVE_INFINITY,ymin:Number.POSITIVE_INFINITY,xmax:Number.NEGATIVE_INFINITY,ymax:Number.NEGATIVE_INFINITY};var m=0;if(b.geometry)for(var f=0,l=c._getQueryBoundList(b.geometry);f<l.length;f++){for(var b=l[f],h=e.search({minX:b[0],minY:b[1],maxX:b[2],maxY:b[3]}).filter(g),d=0,k=h;d<k.length;d++){var n=k[d],b=n[0],n=n[1];a.xmin=Math.min(b,a.xmin);a.ymin=Math.min(n,a.ymin);a.xmax=Math.max(b,a.xmax);a.ymax=Math.max(n,a.ymax)}m+=h.length}else for(var h=e.all().filter(g),
m=m+h.length,e=0,g=h;e<g.length;e++)f=g[e],b=f[0],n=f[1],a.xmin=Math.min(b,a.xmin),a.ymin=Math.min(n,a.ymin),a.xmax=Math.max(b,a.xmax),a.ymax=Math.max(n,a.ymax);return{count:m,extent:new w.Extent(a)}})};d.prototype.executeForIds=function(a){var c=this;return this._normalizeQueryGeometry(a).then(function(a){return k.all([c._checkQuerySupport(a),c._buildSpatialIndex()])}).then(function(a){var b=a[0];a=a[1];var e=c._attributesMap,g=c.objectIdField,d=c._getWhereFilter(b);if(b.geometry){for(var f=[],l=
0,b=c._getQueryBoundList(b.geometry);l<b.length;l++)for(var h=b[l],k=0,h=a.search({minX:h[0],minY:h[1],maxX:h[2],maxY:h[3]}).filter(d);k<h.length;k++)f.push(e.get(h[k])[g]);return f}return a.all().filter(d).map(function(a){return e.get(a)[g]})})};d.prototype._normalizeQueryGeometry=function(a){return y.normalizeCentralMeridian(a.geometry?[a.geometry]:[]).then(function(c){return a.clone().set("geometry",c[0])})};d.prototype._checkQuerySupport=function(a){var c=this._isSupportedQuery(a);return c?k.reject(c):
a};d.prototype._isSupportedQuery=function(a){var c=this;if(null!=a.distance||null!=a.geometryPrecision||a.groupByFieldsForStatistics&&a.groupByFieldsForStatistics.length||null!=a.maxAllowableOffset||a.multipatchOption||null!=a.num||a.orderByFields&&a.orderByFields.length||a.outSpatialReference&&!x.canProject(a.outSpatialReference,this.spatialReference)||a.outStatistics&&a.outStatistics.length||a.pixelSize||a.relationParameter||a.returnDistinctValues||null!=a.start||a.text||a.timeExtent)return new p("spatial-index:unsupported-query",
"Unsupported query");var b=a.where;if(b&&"1\x3d1"!==b){var d=void 0;q.has(b)?d=q.get(b):(d=u.create(b),q.set(b,d));if(!d.isStandardized())return new p("spatial-index:unsupported-query","where clause is using non standard function");if(!d.getFields().every(function(a){return c._fieldsMap.has(a)}))return new p("spatial-index:unsupported-query","where clause is using unknown field")}a=a.geometry;if(!a)return null;if("extent"!==a.type&&"polygon"!==a.type)return new p("spatial-index:unsupported-query",
"Unsupported query geometry type: "+a.type);if("polygon"===a.type)if(a=a.rings,2===a.length){if(!a.every(function(a){return 5!==a.length?!1:a[0][0]===a[1][0]&&a[0][0]===a[4][0]&&a[2][0]===a[3][0]&&a[0][1]===a[3][1]&&a[0][1]===a[4][1]&&a[1][1]===a[2][1]}))return new p("spatial-index:unsupported-query","Unsupported query geometry")}else return new p("spatial-index:unsupported-query","Unsupported query geometry");return null};d.prototype._getWhereFilter=function(a){var c=this;a=a.where;if(q.has(a)){var b=
q.get(a);if(b.isStandardized())return function(a){return b.testFeature(c._attributesMap.get(a))}}return function(a){return!0}};d.prototype._getCreateFeature=function(a){var c=this._attributesMap,b=a.quantizationParameters;if(!a.returnGeometry)return function(a){return{attributes:c.get(a)}};var d,e;if(b){var g=b.extent.xmin,k=b.extent.ymax,f=b.tolerance;d=function(a){return Math.round((a-g)/f)};e=function(a){return Math.round((k-a)/f)}}else d=e=function(a){return a};return function(a){return{geometry:{x:d(a[0]),
y:e(a[1])},attributes:c.get(a)}}};d.prototype._getQueryBoundList=function(a){if("extent"===a.type)return[[a.xmin,a.ymin,a.xmax,a.ymax]];if("polygon"===a.type)return a.rings.map(function(a){return[a[0][0],a[0][1],a[2][0],a[2][1]]})};d.prototype._buildSpatialIndex=function(){if(this._spatialIndex)return k.resolve(this._spatialIndex);var a=this._numFeatures,c=this._attributesMap,b=this._spatialIndex=v(9,["[0]","[1]","[0]","[1]"]),d=this._readCoordinates(0,a);b.load(d);for(var e=0;e<a;e++)c.set(d[e],
this._readFeatureAttributes(e));return k.resolve(b)};d.prototype._createQueryResponse=function(a,c){var b=this,d=a.outSpatialReference,e=a.quantizationParameters;return{features:c,fields:a.outFields.map(function(a){return b._fieldsMap.get(a)}),geometryType:"esriGeometryPoint",objectIdFieldName:this.objectIdField,spatialReference:d?d.toJSON():this.spatialReference.toJSON(),transform:e?{originPosition:"upperLeft",scale:[e.tolerance,e.tolerance],translate:[e.extent.xmin,e.extent.ymax]}:null}};return d}()});