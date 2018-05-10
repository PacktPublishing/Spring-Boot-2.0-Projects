/*
    Copyright (C) 2018  Shazin Sadakath

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.packtpub.springboot2kotlin.advanced

class Functions {

    fun run() {
        withDefaultValues();
        withDefaultValues("Shahim", 32);

        withNamedArguments(firstName = "Shazin", lastName = "Sadakath");

        genericFunction("Shazin");
        genericFunction(123);

        println("Sum of 1, 2, 3 is ${sum(1, 2, 3)}");
        println("Sum of 0, 9, 8, 7, 6, 5, 4, 3, 2, 1 is ${sum(0, 9, 8, 7, 6, 5, 4, 3, 2, 1)}");

        println("acegikmoqsuwy" zip "bdfhjlnprtvxz");

        outerFunction("Hello World!");
    }

    fun withDefaultValues(name : String = "Shazin", age : Int = 32) {
        println("Name $name and Age $age");
    }

    fun withNamedArguments(firstName : String, lastName : String) {
        println("Hello Mr. $firstName, $lastName");
    }

    fun <T> genericFunction(t : T) {
        println(t);
    }

    fun sum(vararg nos: Int): Int {
        var result = 0;
        for(no in nos) {
            result+= no;
        }
        return result;
    }

    infix fun String.zip(s1 : String) : String {
        var result : String = "";
        var zipLength : Int = Math.min(s1.length, this.length);
        for(i in 0..zipLength-1) {
            result += this[i];
            result += s1[i];
        }
        return result;
    }

    fun outerFunction(o : String) {
        var outerFunctionVariable = 0;
        fun innerFunction(i : String) {
            outerFunctionVariable += 1;
            println("Inner Function $i");
            println("Outer Function Variable inside Inner Function $outerFunctionVariable");
        }

        innerFunction(o);

        println("Outer Function $o");
        println("Outer Function Variable $outerFunctionVariable");
    }


}
