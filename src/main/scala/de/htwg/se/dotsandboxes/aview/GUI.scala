package de.htwg.se.dotsandboxes
package aview

import controller.Controller
import model.Move
import util.{Observer, Event}

import scala.swing.*
import scala.swing.event.*
import javax.swing.ImageIcon
import java.awt.Color
import javax.imageio.ImageIO
import java.io.File

class GUI(controller: Controller) extends Frame with Observer:
    controller.add(this)

    title = "Dots And Boxes"
    menuBar = new MenuBar {
        contents += new Menu("File") {
            contents += new MenuItem(Action("Exit") { controller.eventAbort })
            contents += new MenuItem(Action("Undo") { controller.publish(controller.undo) })
            contents += new MenuItem(Action("Redo") { controller.publish(controller.redo) })
        }
    }
    contents = updateContents
    pack
    centerOnScreen
    open()


    override def update(e: Event): Unit = e match
        case Event.Abort => sys.exit
        case Event.End   => contents = finalStats; repaint
        case Event.Move  => contents = updateContents; repaint

    def updateContents =
        new BorderPanel {
            background = Color.WHITE
            val label = new Label(s"${controller.currentPlayer}s turn\n [points: ${controller.currentPoints}]")
            label.font = new Font("Arial", 0, 30)
            add(label, BorderPanel.Position.North)
            add(new CellPanel(controller.field.colSize(1, 0), controller.field.rowSize(2)), BorderPanel.Position.Center)
        }

    def finalStats =
        new BorderPanel {
            background = Color.WHITE
            val stats = new Label(s"${controller.stats}")
            stats.font = new Font("Arial", 0, 30)
            add(stats, BorderPanel.Position.Center)
            val winner = new Label(s"${controller.winner}")
            winner.font = new Font("Arial", 0, 30)
            add(winner, BorderPanel.Position.South)
        }


    class CellPanel(x: Int, y: Int) extends GridPanel((y + y + 1), (x + x + 1)):
        val dim = new Dimension(900, 700)
        minimumSize = dim
        maximumSize = dim
        preferredSize = dim

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
                    case "-" => icon = new ImageIcon(ImageIO.read(new File("src/resources/Empty.png")))
                    case "B" => icon = new ImageIcon(ImageIO.read(new File("src/resources/BlueTaken.png")))
                    case "R" => icon = new ImageIcon(ImageIO.read(new File("src/resources/RedTaken.png")))
                    case "G" => icon = new ImageIcon(ImageIO.read(new File("src/resources/GreenTaken.png")))
                    case "Y" => icon = new ImageIcon(ImageIO.read(new File("src/resources/YellowTaken.png")))
            }

        private def dot = new Label {
            icon = new ImageIcon(ImageIO.read(new File("src/resources/Dot.png")))}
            background = Color.WHITE


    class CellButton(vec: Int, x: Int, y: Int, status: Boolean) extends Button:
        borderPainted = false
        focusPainted = false
        focusable = false
        background = Color.WHITE

        vec match
            case 1 => status match
                case true => icon = new ImageIcon(ImageIO.read(new File("src/resources/BarTaken.png")))
                case false => icon = new ImageIcon(ImageIO.read(new File("src/resources/BarUntaken.png")))
            case 2 => status match
                case true => icon = new ImageIcon(ImageIO.read(new File("src/resources/ColTaken.png")))
                case false => icon = new ImageIcon(ImageIO.read(new File("src/resources/ColUntaken.png")))
        
        listenTo(mouse.clicks)
        reactions += {
            case MouseClicked(src, pt, mod, clicks, props) => {
                controller.publish(controller.put, Move(vec, x, y, true))
            }
        }
