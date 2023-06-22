package de.htwg.se.dotsandboxes.model
package fileIoComponent

import fieldComponent.FieldInterface


trait FileIOInterface:
    def save(field: FieldInterface): Unit
    def load: FieldInterface
  
