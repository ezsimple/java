/*******************************
	Class Version 1.1.2
	
	A very simple "Object Oriented Programming Structured" class system for JavaScript

	The MIT License (MIT)

	Copyright (c) 2013 Greg McLeod <cleod9{at}gmail.com>

	Permission is hereby granted, free of charge, to any person obtaining a copy
	of this software and associated documentation files (the "Software"), to deal
	in the Software without restriction, including without limitation the rights
	to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
	copies of the Software, and to permit persons to whom the Software is
	furnished to do so, subject to the following conditions:

	The above copyright notice and this permission notice shall be included in
	all copies or substantial portions of the Software.

	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
	IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
	FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
	AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
	LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
	OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
	THE SOFTWARE.
*******************************/
var Class = (function() {
	//Constructor
	var Class = function() {}; //Unique ID for doing Class type checks (comparable via Class.typeMatch())
	//Extender (copy all of the prototypes from parent to child)
	Class._ID_ = 0;
	Class.extend = function(props) {
		var i;
		//Create a new empty function to populate (This is the Class that will be returned)
		//Note: "constructor" will be used as the base if provided)
		var child = (typeof props.constructor == 'function') ? props.constructor : function() {};
		//Child will use provided fields as their prototype
		child.prototype = (typeof props == 'object') ? props : {};
		//Child inherits the fields from parent that it doesn't have yet
		for(i in this.prototype)
			if(!child.prototype.hasOwnProperty(i))
				child.prototype[i] = this.prototype[i];
		//Statics get placed onto the child itself
		if(child.prototype._statics_) {
			for(i in child.prototype._statics_)
				if(child.prototype._statics_.hasOwnProperty(i))
					child[i] = child.prototype._statics_[i];
			//Remove _statics_ from the prototype
			delete child.prototype._statics_;
		}
		//Pass down the extend method
		child.extend = this.extend;
		//Pass down the superclass method (if applicable)
		child.prototype.superclass = child.superclass = (this != Class) ? this : null;
		//Grant unique class ID
		child.prototype._ID_ = child._ID_ = ++Class._ID_;
		//Return the completed class
		return child;
	};
	Class.typeMatch = function(class1, class2) {
		//If the classes have matching _ID_ fields they are of the same type
		//Note: Classes have _ID_ as a static and prototype value, use instanceof for strict type testing
		return (class1._ID_ == class2._ID_);
	};
	Class.descendantOf = function (child, parent) {
		//Recursively traverse back up until we find a valid ancestor
		//Note: Classes have _ID_ as a static and prototype value, use instanceof for strict type testing
		if(child.superclass) {
			if(child.superclass._ID_ == parent._ID_)
				return true;
			else
				return Class.descendantOf(child.superclass, parent); 
		}
		return false;
	};
	//Return
	return Class;
})();