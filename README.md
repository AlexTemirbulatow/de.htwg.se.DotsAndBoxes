# Dots And Boxes

A project at HTWG Konstanz for the class Software Engineering to create a game 'Dots and Boxes' in scala. <br>
Using fuctional programming as a paradigm for software development and agility by testing code. <br><br>
Click here to view the <a href="https://docs.google.com/presentation/d/15wLZfl3zXVfde_VmGHMUGKwv8NFT9vJNmobpuDwV2Hc/edit#slide=id.p">project presentation</a>


[![Scala CI](https://github.com/AlexTemirbulatow/de.htwg.se.DotsAndBoxes/actions/workflows/scala.yml/badge.svg?branch=developer)](https://github.com/AlexTemirbulatow/de.htwg.se.DotsAndBoxes/actions/workflows/scala.yml)
[![Coverage Status](https://coveralls.io/repos/github/AlexTemirbulatow/de.htwg.se.DotsAndBoxes/badge.svg?branch=developer)](https://coveralls.io/github/AlexTemirbulatow/de.htwg.se.DotsAndBoxes?branch=developer)
<img alt="GitHub contributors" src="https://img.shields.io/github/contributors/AlexTemirbulatow/de.htwg.se.DotsAndBoxes">
<br><br>
<img alt="GitHub watchers" src="https://img.shields.io/github/watchers/AlexTemirbulatow/de.htwg.se.DotsAndBoxes?style=social">



## Usage
You can compile code with `sbt compile`, run it with `sbt run`, and `sbt console` will start a Scala 3 REPL.

You can run tests with `sbt clean coverage test` and get a coverage report with `sbt coverageReport`

To run the TUI properly, the terminal must be set to `chcp 65001` and `Unicode UTF-8` must be enabled.
<br><br>

<table>
    <tr><th>Rules</th><th>GUI</th></tr>
    <tr><td>You move by taking a horizontal or vertical line <br>
            between two dots. When you place the last line that <br>
            forms a box, the box and its content are yours. The <br>
            players move in turn, but whenever a player takes a <br>
            box, they must move again. One box is one point. 
            <br><br>
            The game ends when all boxes have been taken.
            <br><br>
            The player with the highest score wins. <br>
            It's a tie if two players got the same highest score. 
            <br><br>
            The UIs support 2-4 players, a customizable field <br>
            size and a GUI only light and dark mode.
    </td><td><p align="center">
             <img src="https://github.com/AlexTemirbulatow/de.htwg.se.DotsAndBoxes/blob/developer/src/resources/5_DotsAndBoxesGUI_Dark.jpg" width="390" height="380"></a></td></tr>
    <tr><th>Usage</th><th>TUI</th></tr>
    <tr><td>
            The field consists of two separate 2D vectors: <br>
            <ul>
                <li>The first vector represents all horizontal lines</li>
                <li>The second vector represents all vertical lines</li>
            </ul>
            Access in a move:
            <ul>
                <li>The horizontal vector can be accessed with (1)</li>
                <li>The vertical vector can be accessed with (2)</li>
                <li>Lines within a vector are accessed with<br>X and Y coordinates, starting at (0,0)</li>
            </ul>
            Therefore, a move to occupy a line consists of: <br>
            &lt;Line&gt;&lt;X&gt;&lt;Y&gt;
            <br><br>
            You can type (q) to quit, (z) to undo, (y) to redo, <br>
            (s) to save the current game state and (l) to load it.
    </td><td><p align="center">
             <img src="https://github.com/AlexTemirbulatow/de.htwg.se.DotsAndBoxes/blob/developer/src/resources/5_DotsAndBoxesTUI.png" width="390" height="370"></a></td></tr>
</table>



## Contributors
<table>
    <tr><th><a href="https://github.com/AlexTemirbulatow">AlexTemirbulatow</a></th><th><a href="https://github.com/Yazanski">Yazanski</a></th></tr>
    <tr><td><a href="https://git.io/streak-stats"><img src="https://streak-stats.demolab.com?user=AlexTemirbulatow&theme=dark"/></a></td><td><a href="https://git.io/streak-stats"><img src="https://streak-stats.demolab.com?user=Yazanski&theme=dark"/></a></td></tr>
</table>



## LICENSE
<img alt="GitHub" src="https://img.shields.io/github/license/AlexTemirbulatow/de.htwg.se.dotsandboxes">

Copyright (c) 2023 Alexander Temirbulatow and Yazan Alkhatib
