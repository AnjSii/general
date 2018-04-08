// All material copyright ESRI, All Rights Reserved, unless otherwise specified.
// See https://js.arcgis.com/4.6/esri/copyright.txt for details.
//>>built
define("require exports ../../../core/tsSupport/declareExtendsHelper ../../../core/tsSupport/decorateHelper ../../../core/accessorSupport/decorators ../../../layers/graphics/controllers/StreamController ../../layers/StreamLayerView ./GraphicLayerView3DBase ../../../core/Error ../../../core/promiseUtils".split(" "),function(m,n,e,c,b,f,g,h,k,l){return function(d){function a(){var a=d.call(this)||this;a.labelingEnabled=!0;return a}e(a,d);a.prototype.createController=function(){var a=this;return this.layer.createGraphicsController({layerView:this}).then(function(b){if(b instanceof
f)return a.controller=b;throw Error("Invalid controller created.");}).otherwise(function(a){return l.reject(new k("streamlayerview3d:create-controller",a.message))})};c([b.property()],a.prototype,"controller",void 0);c([b.property({aliasOf:"controller.graphics",readOnly:!0})],a.prototype,"graphics",void 0);return a=c([b.subclass("esri.views.3d.layers.StreamLayerView3D")],a)}(b.declared(g,h))});