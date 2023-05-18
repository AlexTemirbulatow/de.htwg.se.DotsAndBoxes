# Dots and Boxes

A project at HTWG Konstanz for the class Software Engineering to create a game 'Dots and Boxes' in scala. <br>
Using fuctional programming as a paradigm for software development and agility by testing code. <br>



[![Scala CI](https://github.com/AlexTemirbulatow/de.htwg.se.DotsAndBoxes/actions/workflows/scala.yml/badge.svg?branch=developer)](https://github.com/AlexTemirbulatow/de.htwg.se.DotsAndBoxes/actions/workflows/scala.yml)
[![Coverage Status](https://coveralls.io/repos/github/AlexTemirbulatow/de.htwg.se.DotsAndBoxes/badge.svg?branch=developer)](https://coveralls.io/github/AlexTemirbulatow/de.htwg.se.DotsAndBoxes?branch=developer)
<img alt="GitHub contributors" src="https://img.shields.io/github/contributors/AlexTemirbulatow/de.htwg.se.DotsAndBoxes">
<br><br>
<img alt="GitHub watchers" src="https://img.shields.io/github/watchers/AlexTemirbulatow/de.htwg.se.DotsAndBoxes?style=social">

## Usage
You can compile code with `sbt compile`, run it with `sbt run`, and `sbt console` will start a Scala 3 REPL.

You can run tests with `sbt clean coverage test` and get a coverage report with `sbt coverageReport` 
<br><br>

<table>
    <tr><th>Rules</th><th>Field</th></tr>
    <tr><td>You move by connecting two dots with a horizontal or <br>
            vertical line. When you place the last 'wall' of a single <br>
            box, the box and its contents are yours. The players <br>
            move in turn but whenever a player takes a box they <br>
            must move again. One finished box is one point. 
            <br><br>
            The game ends when all the boxes have been taken.
            <br><br>
            You win if you got more points than Player 2. <br>
            It's a draw if both players got the same amount of points.
    </td><td><p align="center">
             <img src="https://github.com/AlexTemirbulatow/de.htwg.se.DotsAndBoxes/blob/main/dotsandboexs.png" width="390" height="340"></a></td></tr>
</table>

## Contributors
<table>
    <tr><th><a href="https://github.com/AlexTemirbulatow">AlexTemirbulatow</a></th><th><a href="https://github.com/Yazanski">Yazanski</a></th></tr>
    <tr><td><a href="https://git.io/streak-stats"><img src="https://streak-stats.demolab.com?user=AlexTemirbulatow&theme=dark"/></a></td><td><a href="https://git.io/streak-stats"><img src="https://streak-stats.demolab.com?user=Yazanski&theme=dark"/></a></td></tr>
</table>



## LICENSE
<img alt="GitHub" src="https://img.shields.io/github/license/AlexTemirbulatow/de.htwg.se.dotsandboxes">

Copyright (c) 2023 Alexander Temirbulatow and Yazan Alkhatib
