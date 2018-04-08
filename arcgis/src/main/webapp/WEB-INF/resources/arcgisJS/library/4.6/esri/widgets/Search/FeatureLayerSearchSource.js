// All material copyright ESRI, All Rights Reserved, unless otherwise specified.
// See https://js.arcgis.com/4.6/esri/copyright.txt for details.
//>>built
define("require exports ../../core/tsSupport/declareExtendsHelper ../../core/tsSupport/decorateHelper ../../core/tsSupport/assignHelper ../../core/lang ../../layers/FeatureLayer ./SearchSource ./support/featureLayerUtils ../../core/accessorSupport/decorators".split(" "),function(p,q,l,c,h,e,m,n,k,d){return function(f){function a(){var b=null!==f&&f.apply(this,arguments)||this;b.displayField=null;b.exactMatch=null;b.searchFields=null;b.searchQueryParams=null;b.searchTemplate=null;b.suggestionTemplate=
null;b.suggestQueryParams=null;return b}l(a,f);g=a;Object.defineProperty(a.prototype,"featureLayer",{set:function(b){this._set("featureLayer",b);this._loadFeatureLayer()},enumerable:!0,configurable:!0});a.prototype.clone=function(){return new g({autoNavigate:this.autoNavigate,filter:this.filter,maxResults:this.maxResults,maxSuggestions:this.maxSuggestions,minSuggestCharacters:this.minSuggestCharacters,outFields:this.outFields?e.clone(this.outFields):null,placeholder:this.placeholder,popup:this.popup,
popupEnabled:this.popupEnabled,popupOpenOnSelect:this.popupOpenOnSelect,prefix:this.prefix,resultGraphicEnabled:this.resultGraphicEnabled,resultSymbol:this.resultSymbol?this.resultSymbol.clone():null,searchExtent:this.searchExtent?this.searchExtent.clone():null,suggestionsEnabled:this.suggestionsEnabled,suffix:this.suffix,withinViewEnabled:this.withinViewEnabled,displayField:this.displayField,exactMatch:this.exactMatch,featureLayer:this.featureLayer,searchFields:this.searchFields?e.clone(this.searchFields):
null,searchQueryParams:this.searchQueryParams?e.clone(this.searchQueryParams):null,suggestionTemplate:this.suggestionTemplate,suggestQueryParams:this.suggestQueryParams?e.clone(this.suggestQueryParams):null,zoomScale:this.zoomScale})};a.prototype.getResults=function(b){return k.getResults(h({source:this},b))};a.prototype.getSuggestions=function(b){return k.getSuggestions(h({source:this},b))};a.prototype._getFirstStringField=function(){var b=this.featureLayer,a="";b&&b.fields&&b.fields.some(function(b){if("string"===
b.type)return a=b.name,!0});return a};a.prototype._getDisplayField=function(){return this.displayField||this.featureLayer.displayField||this._getFirstStringField()};a.prototype._setFallbackNameFromFeatureLayer=function(b){(b=this._getFeatureLayerTitle(b))&&!this.name&&this._set("name",b)};a.prototype._loadFeatureLayer=function(){var b=this,a=this.featureLayer;a&&a.load().then(function(){b.featureLayer===a&&b._setFallbackNameFromFeatureLayer(a)})};a.prototype._getFeatureLayerTitle=function(b){var a=
b.title,d="";(this.searchFields||[this._getDisplayField()]).forEach(function(a,c){d+=0===c?": ":", ";c=b.getField(a);d+=c&&c.alias||a});return a+d};c([d.property({json:{read:{source:"field.name"},write:{target:"field.name"}}})],a.prototype,"displayField",void 0);c([d.property({json:{read:{source:"field.exactMatch"},write:{target:"field.exactMatch"}}})],a.prototype,"exactMatch",void 0);c([d.property({type:m,value:null})],a.prototype,"featureLayer",null);c([d.property()],a.prototype,"searchFields",
void 0);c([d.property()],a.prototype,"searchQueryParams",void 0);c([d.property()],a.prototype,"searchTemplate",void 0);c([d.property()],a.prototype,"suggestionTemplate",void 0);c([d.property()],a.prototype,"suggestQueryParams",void 0);return a=g=c([d.subclass("esri.widgets.Search.FeatureLayerSearchSource")],a);var g}(d.declared(n))});