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
package com.packtpub.springboot2kotlin.gettingstarted

class GettingStarted {
    fun run() {
        val calculator = Calculator();
        val stringFormatter = StringFormatter();
        calculator.addAndPrint(1, 2);
        calculator.substractAndPrint(2, 1);
        var sum: Double = calculator.add(1.0, 2.0);
        println("Double Sum of 1.0 + 2.0 = $sum");

        stringFormatter.trimAndPrint("A,B,C                                ");
        stringFormatter.printCsvItems("A,B,C,D,E,F,G,H,I");

        println("No of times methods invoked in StringFormatter = ${stringFormatter.noOfTimesMethodsInvoked}");

        println();

        var animals = arrayOf("Cat", "Lion");

        var index = 0;
        while (index < animals.size) {
            var animal = animals[index++];
            println("This animal is a $animal");
        }

        for (index in animals.indices) {
            println("This is a great animal : ${animals[index]}")
        }

        var letters = stringFormatter.csvToList("A,B,C,D,E,F,G,H,I");

        if (letters != null) {
            for (letter in letters) {
                var word : String = when(letter) {
                    "A" -> "Apple";
                    "B" -> "Ball";
                    "C" -> "Cat";
                    "D" -> "Dog";
                    else -> "Don't Know";
                }

                println("$letter for $word");
            }
        }

        for (i in 1..10) {
            if (i % 2 == 0) {
                println("$i is an even number");
            } else {
                println("$i is an odd number");
            }
        }

        for (i in 10 downTo 0 step 2) {
            println("$i");
        }
    }
}
