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

To run the TUI the terminal must be set to `chcp 65001` and `Unicode UTF-8` must be enabled.
<br><br>

<table>
    <tr><th>Rules</th><th>Field</th></tr>
    <tr><td>You move by connecting two dots with a horizontal or <br>
            vertical line. When you place the last 'wall' of a single <br>
            box, the box and its contents are yours. The players <br>
            move in turn, but whenever a player takes a box they <br>
            must move again. One finished box is one point. 
            <br><br>
            2-4 players are possible.
            <br><br>
            The game ends when all the boxes have been taken.
            <br><br>
            Winner is the player with the most points. <br>
            It's a tie if two players got the same amount of points. 
    </td><td><p align="center">
             <img src="https://github.com/AlexTemirbulatow/de.htwg.se.DotsAndBoxes/blob/main/src/main/scala/de/htwg/se/dotsandboxes/dotsandboxes.png" width="390" height="340"></a></td></tr>
    <tr><th>Usage</th><th>TUI</th></tr>
    <tr><td>To start the TUI game, a player size must be chosen. <br>
            The field size varies based on the player count.
            <br><br>
            The field consists of two separate 2D vectors: <br>
            <ul>
                <li>The first vector represents all horizontal lines</li>
                <li>The Second vector represents all vertical lines</li>
            </ul>
            Accessing in a move:
            <ul>
                <li>The horizontal vector can be accessed with (1)</li>
                <li>The vertical vector can be accessed with (2)</li>
                <li>Lines within a vector are accessed with<br>X and Y coordinates, beginning at (0,0)</li>
            </ul>
            Therefore, a move to occupy a line consists of: <br>
            &lt;Vector&gt;&lt;X&gt;&lt;Y&gt;
    </td><td><p align="center">
             <img src="https://github.com/AlexTemirbulatow/de.htwg.se.DotsAndBoxes/blob/main/src/main/scala/de/htwg/se/dotsandboxes/dotsandboxesTUI.png" width="390" height="365"></a></td></tr>
</table>

## Contributors
<table>
    <tr><th><a href="https://github.com/AlexTemirbulatow">AlexTemirbulatow</a></th><th><a href="https://github.com/Yazanski">Yazanski</a></th></tr>
    <tr><td><a href="https://git.io/streak-stats"><img src="https://streak-stats.demolab.com?user=AlexTemirbulatow&theme=dark"/></a></td><td><a href="https://git.io/streak-stats"><img src="https://streak-stats.demolab.com?user=Yazanski&theme=dark"/></a></td></tr>
</table>



## LICENSE
<img alt="GitHub" src="https://img.shields.io/github/license/AlexTemirbulatow/de.htwg.se.dotsandboxes">

Copyright (c) 2023 Alexander Temirbulatow and Yazan Alkhatib
