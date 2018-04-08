// All material copyright ESRI, All Rights Reserved, unless otherwise specified.
// See https://js.arcgis.com/4.6/esri/copyright.txt for details.
//>>built
define(["require","exports","./UpsampleInfo","../support/PreallocArray"],function(g,f,p,k){function h(a,b,e,c){c=b.call(e,a,c);for(var d=0;4>d;d++){var f=a.children[d];f&&h(f,b,e,c)}}function l(a,b,e){if(Array.isArray(a))for(var c=0;c<a.length;c++)m(a[c],b,e);else m(a,b,e)}function m(a,b,e){for(var c=0;4>c;c++){var d=a.children[c];d&&l(d,b,e)}b.call(e,a)}Object.defineProperty(f,"__esModule",{value:!0});g=function(){function a(a){void 0===a&&(a=100);this.q=new k(a);this._last=null;this.done=!0}a.prototype.reset=
function(a){this.q.clear();a&&this.q.pushEither(a);this._last=null;this.done=0===this.q.length};a.prototype.skip=function(){this._last=null;0===this.q.length&&(this.done=!0)};a.prototype.next=function(){if(this.done)return null;if(null!==this._last){var a=this._last.children;if(a[0])for(var e=4;0<=e;e--){var c=a[e];c&&this.q.push(c)}this._last=null}this._last=this.q.pop();0!==this.q.length||this._last.children[0]||(this.done=!0);return this._last};return a}();f.IteratorPreorder=g;g=function(){function a(a){void 0===
a&&(a=100);this.q=new k(a);this.done=!0}a.prototype.reset=function(a){this.q.clear();this.q.pushEither(a);for(a=0;a<this.q.length;)for(var b=this.q.data[a++],c=0;4>c;c++){var d=b.children[c];d&&this.q.push(d)}this.done=0===this.q.length};a.prototype.next=function(){var a=this.q.pop();this.done=0===this.q.length;return a};return a}();f.IteratorPostorder=g;f.lij2str=function(a,b,e){return a+"/"+b+"/"+e};f.tile2str=function(a){return a.lij[0]+"/"+a.lij[1]+"/"+a.lij[2]};f.traverseTilesPreorder=function(a,
b,e,c){if(Array.isArray(a))for(var d=0;d<a.length;d++)h(a[d],b,e,c);else h(a,b,e,c)};f.traverseTilesPostorder=l;f.fallsWithinLayer=function(a,b,e){if(!b)return!1;var c=b.fullExtent,d=a.extent;if(e){if(d[0]<c.xmin||d[1]<c.ymin||d[2]>c.xmax||d[3]>c.ymax)return!1}else if(c.xmin>d[2]||c.ymin>d[3]||c.xmax<d[0]||c.ymax<d[1])return!1;a=a.parentSurface.tilingScheme.levels[a.lij[0]].scale;return 0<b.minScale&&a>1.00000001*b.minScale||0<b.maxScale&&a<.99999999*b.maxScale?!1:!0};f.isPosWithinTile=function(a,
b){a=a.extent;return b[0]>=a[0]&&b[1]>=a[1]&&b[0]<=a[2]&&b[1]<=a[3]};f.getTileNLevelsUp=function(a,b){for(;0<b;)a=a.parent,b--;return a};f.nextTileInAncestry=function(a,b){var e=a.lij[0]-b.lij[0]-1,c=a.lij[2]>>e,d=0;a.lij[1]>>e&1&&(d+=2);c&1&&(d+=1);return b.children[d]};f.computeUpsampleInfoForAncestor=function(a,b){for(var e=1,c=0,d=0;a!==b;)if(e*=.5,c*=.5,d*=.5,a.lij[2]&1&&(c+=.5),0===(a.lij[1]&1)&&(d+=.5),a=a.parent,null==a)throw Error("tile was not a descendant of ancestorTile");a=p.Pool.acquire();
a.init(b,c,d,e);return a};var n=[null];f.hasVisibleSiblings=function(a){Array.isArray(a)||(n[0]=a,a=n);for(var b=0;b<a.length;b++){var e=a[b],c=e.parent;if(c)for(var d=0;4>d;d++){var f=c.children[d];if(f&&f!==e&&f.visible)return!0}}return!1}});