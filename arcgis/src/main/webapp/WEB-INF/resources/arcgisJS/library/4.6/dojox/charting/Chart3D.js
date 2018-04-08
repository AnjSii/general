//>>built
define("dojo/_base/array dojo/dom dojo/_base/declare dojox/gfx dojox/gfx3d dojo/has dojo/has!dojo-bidi?./bidi/Chart3D".split(" "),function(e,n,f,g,h,k,p){var q={x:0,y:0,z:1},r=h.vector,l=g.normalizedLength,m=f(k("dojo-bidi")?"dojox.charting.NonBidiChart3D":"dojox.charting.Chart3D",null,{constructor:function(a,c,b,d){this.node=n.byId(a);this.surface=g.createSurface(this.node,l(this.node.style.width),l(this.node.style.height));this.view=this.surface.createViewport();this.view.setLights(c.lights,c.ambient,
c.specular);this.view.setCameraTransform(b);this.theme=d;this.walls=[];this.plots=[]},generate:function(){return this._generateWalls()._generatePlots()},invalidate:function(){this.view.invalidate();return this},render:function(){this.view.render();return this},addPlot:function(a){return this._add(this.plots,a)},removePlot:function(a){return this._remove(this.plots,a)},addWall:function(a){return this._add(this.walls,a)},removeWall:function(a){return this._remove(this.walls,a)},_add:function(a,c){e.some(a,
function(a){return a==c})||(a.push(c),this.view.invalidate());return this},_remove:function(a,c){var b=e.filter(a,function(a){return a!=c});return b.length<a.length?(a=b,this.invalidate()):this},_generateWalls:function(){for(var a=0;a<this.walls.length;++a)0<r.dotProduct(q,this.walls[a].normal)&&this.walls[a].generate(this);return this},_generatePlots:function(){for(var a=0,c=h.matrix,b=0;b<this.plots.length;++b)a+=this.plots[b].getDepth();for(--b;0<=b;--b){var d=this.view.createScene();d.setTransform(c.translate(0,
0,-a));this.plots[b].generate(this,d);a-=this.plots[b].getDepth()}return this},setDir:function(a){return this}});return k("dojo-bidi")?f("dojox.charting.Chart3D",[m,p]):m});