// All material copyright ESRI, All Rights Reserved, unless otherwise specified.
// See https://js.arcgis.com/4.6/esri/copyright.txt for details.
//>>built
define(["dojo/_base/lang","../core/Accessor","../core/urlUtils"],function(h,l,m){return l.createSubclass({declaredClass:"esri.tasks.Task",normalizeCtorArgs:function(a,e){if("string"!==typeof a)return a;var f={};a&&(f.url=a);e&&h.mixin(f,e);return f},properties:{normalization:{value:!0},parsedUrl:{value:null,readOnly:!0,dependsOn:["url"],get:function(){return this._parseUrl(this.url)}},requestOptions:{value:null},url:{value:null,type:String}},_parseUrl:function(a){return a?m.urlToObject(a):null},_useSSL:function(){var a=
this.parsedUrl,e=/^http:/i;this.url&&this.set("url",this.url.replace(e,"https:"));a&&a.path&&(a.path=a.path.replace(e,"https:"))},_encode:function(a,e,f){var c,b,g={},d,k;for(d in a)if("declaredClass"!==d&&(c=a[d],b=typeof c,null!==c&&void 0!==c&&"function"!==b))if(h.isArray(c))for(g[d]=[],k=c.length,b=0;b<k;b++)g[d][b]=this._encode(c[b]);else"object"===b?c.toJSON&&(b=c.toJSON(f&&f[d]),"esri.tasks.support.FeatureSet"===c.declaredClass&&b.spatialReference&&(b.sr=b.spatialReference,delete b.spatialReference),
g[d]=e?b:JSON.stringify(b)):g[d]=c;return g}})});