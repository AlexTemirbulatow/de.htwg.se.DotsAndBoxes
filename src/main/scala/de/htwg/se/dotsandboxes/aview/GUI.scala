package de.htwg.se.dotsandboxes
package aview

import controller.Controller
import model.Move
import util.{Observer, Event}

import scala.swing.*
import scala.swing.event.*
import java.io.File
import javax.imageio.ImageIO
import javax.swing.ImageIcon
import java.awt.Color
import javax.swing.UIManager

class GUI(controller: Controller) extends Frame with Observer:
    controller.add(this)

    val fieldSize: (Int, Int) = (controller.colSize(1, 0), controller.rowSize(2))
    val grid: (Int, Int) = ((fieldSize._1 + fieldSize._1 + 1), (fieldSize._2 + fieldSize._2 + 1))
    
    title = "Dots And Boxes"
    iconImage = ImageIO.read(new File("src/resources/4_Ikon.png"))
    menuBar = new MenuBar {
        contents += new Menu("") {
            icon = new ImageIcon(ImageIO.read(new File("src/resources/3_Menu.png")))
            borderPainted = false
            contents += new MenuItem(Action("Exit") { controller.abort })
            contents += new MenuItem(Action("Undo") { controller.publish(controller.undo) })
            contents += new MenuItem(Action("Redo") { controller.publish(controller.redo) })
        }
    }
    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName)
    menuBar.border = Swing.EmptyBorder(0, 10, 0, 0)
    menuBar.background = Color(240, 240, 240)
    update(Event.Move)
    centerOnScreen
    pack
    open()

    override def update(event: Event): Unit = event match
        case Event.Abort => sys.exit
        case Event.End   => new Scoreboard
        case Event.Move  => contents = handle; repaint

    override def closeOperation = controller.abort

    def handle = new BorderPanel {
        val dim = new Dimension(900, 750)
        minimumSize = dim
        maximumSize = dim
        preferredSize = dim
        resizable = false
        background = Color.WHITE
        val label = new Label(s"${controller.currentPlayer}s turn [points: ${controller.currentPoints}]")
        label.font = new Font("Comic Sans MS", 0, 30)
        val stats = new TextArea(controller.stats.replace("\n", "   |   "))
        stats.font = new Font("Comic Sans MS", 0, 17)
        stats.background = Color(220, 220, 220)
        stats.border = Swing.EmptyBorder(0, 35, 0, 0)
        stats.editable = false
        add(label, BorderPanel.Position.North)
        add(new CellPanel(fieldSize._1, fieldSize._2), BorderPanel.Position.Center)
        add(stats, BorderPanel.Position.South)}


    class CellPanel(x: Int, y: Int) extends GridPanel(grid._2, grid._1):
        opaque = false
        fieldBuilder

        private def fieldBuilder =
            for (i <- 0 until y)
                for (j <- 0 until x) bar(i, j)
                contents += dot
                for (j <- 0 until x + 1) cell(i, j)
            for (j <- 0 until x) bar(y, j)
            contents += dot

        private def bar(x: Int, y: Int) =
            contents += dot
            contents += new CellButton(1, x, y, controller.get(1, x, y).toString.toBoolean)

        private def cell(x: Int, y: Int) =
            contents += new CellButton(2, x, y, controller.get(2, x, y).toString.toBoolean)
            if(y != this.x) contents += new Label {
                controller.get(0, x, y).toString match
                    case "-" => icon = new ImageIcon(ImageIO.read(new File("src/resources/2_White.png")))
                    case "B" => icon = new ImageIcon(ImageIO.read(new File("src/resources/2_BlueTaken.png")))
                    case "R" => icon = new ImageIcon(ImageIO.read(new File("src/resources/2_RedTaken.png")))
                    case "G" => icon = new ImageIcon(ImageIO.read(new File("src/resources/2_GreenTaken.png")))
                    case "Y" => icon = new ImageIcon(ImageIO.read(new File("src/resources/2_YellowTaken.png")))}

        private def dot = new Label { icon = new ImageIcon(ImageIO.read(new File("src/resources/0_Dot.png"))) }


    class CellButton(vec: Int, x: Int, y: Int, status: Boolean) extends Button:
        borderPainted = false
        focusPainted = false
        opaque = false

        icon = vec match
            case 1 => status match
                case true => new ImageIcon(ImageIO.read(new File("src/resources/1_BarTaken.png")))
                case false => new ImageIcon(ImageIO.read(new File("src/resources/2_White.png")))
            case 2 => status match
                case true => new ImageIcon(ImageIO.read(new File("src/resources/1_ColTaken.png")))
                case false => new ImageIcon(ImageIO.read(new File("src/resources/2_White.png")))

        listenTo(mouse.moves, mouse.clicks)
        reactions += {
            case MouseClicked(src, pt, mod, clicks, props) =>
                controller.publish(controller.put, Move(vec, x, y, true))

            case MouseEntered(source) => vec match
                case 1 => status match
                    case true =>
                    case false => icon = new ImageIcon(ImageIO.read(new File("src/resources/1_BarUntaken.png")))
                case 2 => status match
                    case true =>
                    case false => icon = new ImageIcon(ImageIO.read(new File("src/resources/1_ColUntaken.png")))

            case MouseExited(source) => status match
                case true =>
                case false => icon = new ImageIcon(ImageIO.read(new File("src/resources/2_White.png")))}


    class Scoreboard extends Frame:
        title = "Scoreboard"
        iconImage = ImageIO.read(new File("src/resources/4_Ikon.png"))
        contents = new BorderPanel {
            val dim = new Dimension(300, 200)
            minimumSize = dim
            maximumSize = dim
            preferredSize = dim
            resizable = false
            val winner = new Label(s"${controller.winner}")
            winner.font = new Font("Comic Sans MS", 0, 30)
            val stats = new TextArea(controller.stats)
            stats.font = new Font("Arial", 0, 15)
            stats.background = new Color(220, 220, 220)
            stats.border = Swing.EmptyBorder(0, 70, 0, 0)
            stats.editable = false
            add(winner, BorderPanel.Position.Center)
            add(stats, BorderPanel.Position.South)}
        pack
        centerOnScreen
        open()
