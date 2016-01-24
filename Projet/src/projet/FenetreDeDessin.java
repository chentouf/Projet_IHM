/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hello
 */
public class FenetreDeDessin extends javax.swing.JFrame {
   

    private enum STATE {
        INIT,
        REPOS_AJOUTER , PRESED_AJOUTER, PRESEDMOUVED_AJOUTER, 
        REPOS_DEPLACER, PRESED_DEPLACER,PRESEDMOUVED_DEPLACER, 
        REPOS_MODIFIER, PRESED_MODIFIER, PRESEDMOUVED_MODIFIER, 
        REPOS_SUPPRIMER, PRESED_SUPPRIMER
    };
    
    private STATE state;
    private List<Line2D.Float> lineList;
    private Line2D.Float tmpLine;
    private Line2D.Float deplacedLine;
    private Line2D.Float modifiedLine1;
    private Line2D.Float modifiedLine2;
    private Point2D p1;
    private Point2D p2;
    private Point2D origin;
    private Point2D originDeplacement;
    private Point2D pointDeplacement;
    double x;
    double y;
            
    boolean init = false;
    boolean ajouter = true;
    boolean modifier = false;
    boolean deplacer = false;
    boolean supprimer = false;
        
    public FenetreDeDessin() {
        initComponents();
        initialisation();  
    }
    
    private void tracer2lines(Line2D.Float l, Point2D p1, Point2D p2) {
                         
        modifiedLine1 = new Line2D.Float(p1, origin);
        modifiedLine2 = new Line2D.Float(origin,p2);
        lineList.remove(l);
        repaint();
    }
    
    private void traceRedLine() {
        repaint();
    }
    
    
    private void traceBlacLine() {
        lineList.add(new Line2D.Float(tmpLine.getP1(), tmpLine.getP2()));
        tmpLine.setLine(0, 0, 0, 0);
        if(deplacedLine != null){
            lineList.add(deplacedLine);
            deplacedLine = null;
        }
        if(modifiedLine1 != null && modifiedLine2 != null)
        {
            lineList.add(modifiedLine1);
            lineList.add(modifiedLine2);
            modifiedLine2 = null;
            modifiedLine1 = null;
        }
        repaint();
    }
    
    private void deplacerLine(Line2D.Float line, Point2D p1, Point2D p2, Point2D o) {
        double x1 = o.getX() - originDeplacement.getX() + p1.getX();
        double y1 = o.getY() - originDeplacement.getY() + p1.getY();
        double x2 = o.getX() - originDeplacement.getX() + p2.getX();
        double y2 = o.getY() - originDeplacement.getY() + p2.getY();
        deplacedLine.setLine(x1, y1, x2, y2);
        lineList.remove(line);
        repaint();
    }
    
    private void deplacerLine(Point2D p1, Point2D p2, Point2D o) {
        double x1 = o.getX() - originDeplacement.getX() + p1.getX();
        double y1 = o.getY() - originDeplacement.getY() + p1.getY();
        double x2 = o.getX() - originDeplacement.getX() + p2.getX();
        double y2 = o.getY() - originDeplacement.getY() + p2.getY();
        deplacedLine.setLine(x1, y1, x2, y2);
        repaint();
    }
    

    private void setP1(int x, int y) {
        double x2 = tmpLine.getX2();
        double y2 = tmpLine.getY2();
        tmpLine.setLine(x, y, x2, y2);
    }
    
    private void setP2(int x, int y) {
        double x1 = tmpLine.getX1();
        double y1 = tmpLine.getY1();
        tmpLine.setLine(x1, y1, x, y);  
    }
    
    
    private void traceModifiedLine() {
        repaint();
    }
    
    
    private void setOrigine(int x, int y){
        double x1 = modifiedLine1.getX1();
        double y1 = modifiedLine1.getY1();
        double x2 = modifiedLine2.getX2();
        double y2 = modifiedLine2.getY2();
        modifiedLine1.setLine( x1, y1,x, y);
        modifiedLine2.setLine(x, y,x2, y2);
    }
    
    
    private void initialisation() {
        state = STATE.INIT;
        lineList = new ArrayList();
        tmpLine = new Line2D.Float(); 
        btnDeplacer.setEnabled(false);
        btnModifier.setEnabled(false);
        btnSupprimer.setEnabled(false);
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke());
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.50f));
        g2.setColor(Color.black);
        for (Line2D.Float l: lineList) {
            g2.draw(l);
        }
        g2.setColor(Color.blue);
        g2.draw(tmpLine);
        
        if(deplacedLine != null){
            g2.setColor(Color.blue);
            g2.draw(deplacedLine);
        }
        if(modifier && modifiedLine1!= null && modifiedLine2 != null)
        {
            g2.draw(modifiedLine1);
            g2.draw(modifiedLine2);
        
        }
            
    }
    
    public void contains2(Point p){
       
    }
    
    public void supprimerLine(Line2D.Float line){
        lineList.remove(line);
        repaint();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnDeplacer = new javax.swing.JButton();
        btnModifier = new javax.swing.JButton();
        btnAjouter = new javax.swing.JButton();
        btnSupprimer = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                formMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formMouseEntered(evt);
            }
        });

        btnDeplacer.setText("Deplacer");
        btnDeplacer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeplacerActionPerformed(evt);
            }
        });

        btnModifier.setText("Modifier");
        btnModifier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModifierActionPerformed(evt);
            }
        });

        btnAjouter.setText("Ajouter");
        btnAjouter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAjouterActionPerformed(evt);
            }
        });

        btnSupprimer.setText("Supprimer");
        btnSupprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSupprimerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(btnAjouter)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDeplacer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnModifier)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSupprimer)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(300, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSupprimer)
                    .addComponent(btnAjouter)
                    .addComponent(btnModifier)
                    .addComponent(btnDeplacer))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
                

    }//GEN-LAST:event_formMouseClicked

    private void btnAjouterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAjouterActionPerformed
        
        switch(state) {
            case INIT:
                state = STATE.REPOS_AJOUTER;
                btnDeplacer.setEnabled(true);
                btnModifier.setEnabled(true);
                btnSupprimer.setEnabled(true);
                init = true;
                break;
            case REPOS_AJOUTER:
                state = STATE.REPOS_AJOUTER;
                break;
            case PRESED_AJOUTER:
                throw new RuntimeException("Impossible action");
            case PRESEDMOUVED_AJOUTER:
                throw new RuntimeException("Impossible action");
            case REPOS_DEPLACER:
                state = STATE.REPOS_AJOUTER;
                break;
            case PRESED_DEPLACER:
                throw new RuntimeException("Impossible action");
            case PRESEDMOUVED_DEPLACER:
                throw new RuntimeException("Impossible action");
            case REPOS_MODIFIER:
                state = STATE.REPOS_AJOUTER;
                break;
            case PRESED_MODIFIER:
                throw new RuntimeException("Impossible action");
            case PRESEDMOUVED_MODIFIER:
                throw new RuntimeException("Impossible action");
            case REPOS_SUPPRIMER:
                state = STATE.REPOS_AJOUTER;
                break;
            case PRESED_SUPPRIMER:
                throw new RuntimeException("Impossible action");
        } 
    }//GEN-LAST:event_btnAjouterActionPerformed

    private void btnDeplacerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeplacerActionPerformed
        switch(state) {
            case INIT:
                throw new RuntimeException("Impossible action");
            case REPOS_AJOUTER:
                if(lineList.size()>0)
                    state = STATE.REPOS_DEPLACER;
                else
                    throw new RuntimeException("pas de ligne");
               break;
            case PRESED_AJOUTER:
                throw new RuntimeException("Impossible action");
            case PRESEDMOUVED_AJOUTER:
                throw new RuntimeException("Impossible action");
                
            case REPOS_DEPLACER:
                state = STATE.REPOS_DEPLACER;
                break;
            case PRESED_DEPLACER:
                throw new RuntimeException("Impossible action");

            case PRESEDMOUVED_DEPLACER:
                throw new RuntimeException("Impossible action");
            case REPOS_MODIFIER:
                state = STATE.REPOS_DEPLACER;
                break;
            case PRESED_MODIFIER:
                throw new RuntimeException("Impossible action");
            case PRESEDMOUVED_MODIFIER:
                throw new RuntimeException("Impossible action");
            case REPOS_SUPPRIMER:
                    if(lineList.size()>0)
                    state = STATE.REPOS_DEPLACER;
               break;
            case PRESED_SUPPRIMER:
                throw new RuntimeException("Impossible action");
        } 
    }//GEN-LAST:event_btnDeplacerActionPerformed

    private void btnModifierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModifierActionPerformed
       modifier = true;
        switch(state) {
            case INIT:
                throw new RuntimeException("Impossible action");
            case REPOS_AJOUTER:
                if(lineList.size()>0)
                    state = STATE.REPOS_MODIFIER;
                else
                    System.err.println("il faut au moins une ligne");
               break;
            case PRESED_AJOUTER:
                throw new RuntimeException("Impossible action");
                
            case PRESEDMOUVED_AJOUTER:
                throw new RuntimeException("Impossible action");
                
            case REPOS_DEPLACER:
                state = STATE.REPOS_MODIFIER;
                break;
            case PRESED_DEPLACER:
                throw new RuntimeException("Impossible action");

            case PRESEDMOUVED_DEPLACER:
                throw new RuntimeException("Impossible action");
                
            case REPOS_MODIFIER:
                state = STATE.REPOS_MODIFIER;
                
                break;
            case PRESED_MODIFIER:
                throw new RuntimeException("Impossible action");
            case PRESEDMOUVED_MODIFIER:
                throw new RuntimeException("Impossible action");
                
            case REPOS_SUPPRIMER:
                    if(lineList.size()>0)
                    state = STATE.REPOS_MODIFIER;
               break;
            case PRESED_SUPPRIMER:
                throw new RuntimeException("Impossible action");
       }
     }//GEN-LAST:event_btnModifierActionPerformed

    private void btnSupprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupprimerActionPerformed
        supprimer = true;
        switch(state) {
            case INIT:
                throw new RuntimeException("Impossible action");
            case REPOS_AJOUTER:
                if(lineList.size()>0)
                    state = STATE.REPOS_SUPPRIMER;
               break;
            case PRESED_AJOUTER:
                throw new RuntimeException("Impossible action");
            case PRESEDMOUVED_AJOUTER:
                throw new RuntimeException("Impossible action");
            case REPOS_DEPLACER:
                state = STATE.REPOS_SUPPRIMER;
                break;
            case PRESED_DEPLACER:
                throw new RuntimeException("Impossible action");
            case PRESEDMOUVED_DEPLACER:
                throw new RuntimeException("Impossible action");
            case REPOS_MODIFIER:
                state = STATE.REPOS_SUPPRIMER;
                break;
            case PRESED_MODIFIER:
                throw new RuntimeException("Impossible action");
            case PRESEDMOUVED_MODIFIER:
                throw new RuntimeException("Impossible action");
            case REPOS_SUPPRIMER:
               state = STATE.REPOS_SUPPRIMER;
               break;
            case PRESED_SUPPRIMER:
                throw new RuntimeException("Impossible action");
       }
    }//GEN-LAST:event_btnSupprimerActionPerformed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
    
        if(init){

        switch(state) {
            case INIT:
                state = STATE.INIT;
                break;
            case REPOS_AJOUTER:
                state = STATE.REPOS_AJOUTER;
                break;            
            case PRESED_AJOUTER:
                state = STATE.PRESEDMOUVED_AJOUTER;
                setP2(evt.getX(), evt.getY());
                traceRedLine();
                break;
            case PRESEDMOUVED_AJOUTER:
                state = STATE.PRESEDMOUVED_AJOUTER;
                setP2(evt.getX(), evt.getY());
                traceRedLine();
                break;
            case REPOS_DEPLACER:
                state = STATE.REPOS_DEPLACER;
                break;
            case PRESED_DEPLACER:
                state = STATE.PRESEDMOUVED_DEPLACER;
                pointDeplacement = evt.getPoint();
                deplacerLine(p1,p2,pointDeplacement);
                break;
            case PRESEDMOUVED_DEPLACER:
                state = STATE.PRESEDMOUVED_DEPLACER;
                pointDeplacement = evt.getPoint();
                deplacerLine(p1,p2,pointDeplacement);
                break;
            case REPOS_MODIFIER:
                // A implementer
                
                break;
            case PRESED_MODIFIER:
                state = STATE.PRESEDMOUVED_MODIFIER;
                setOrigine(evt.getX(), evt.getY());
                traceModifiedLine();
                break;
            case PRESEDMOUVED_MODIFIER:
                state = STATE.PRESEDMOUVED_MODIFIER;
                setOrigine(evt.getX(), evt.getY());
                traceModifiedLine();
                break;
            case REPOS_SUPPRIMER:
                throw new RuntimeException("Impossible action");
            case PRESED_SUPPRIMER:
                throw new RuntimeException("Impossible action");
       
      }
       
        }
    }//GEN-LAST:event_formMouseDragged

    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved

        if(init)
        {
        switch(state) {
            case INIT:
                state = STATE.INIT;
                break;
            case REPOS_AJOUTER:
                state = STATE.REPOS_AJOUTER;
                break;            
            case PRESED_AJOUTER:
                state = STATE.PRESEDMOUVED_AJOUTER;
                setP2(evt.getX(), evt.getY());
                traceRedLine();
                break;
            case PRESEDMOUVED_AJOUTER:
                state = STATE.PRESEDMOUVED_AJOUTER;
                setP2(evt.getX(), evt.getY());
                traceRedLine();
                break;
            case REPOS_DEPLACER:
                state = STATE.REPOS_DEPLACER;
                break;
            case PRESED_DEPLACER:
                state = STATE.PRESEDMOUVED_DEPLACER;
                pointDeplacement = evt.getPoint();
                deplacerLine(p1,p2,pointDeplacement);
                break;
            case PRESEDMOUVED_DEPLACER:
                state = STATE.PRESEDMOUVED_DEPLACER;
                pointDeplacement = evt.getPoint();
                deplacerLine(p1,p2,pointDeplacement);
                break;
            case REPOS_MODIFIER:
                state = STATE.REPOS_MODIFIER;
                break;
            case PRESED_MODIFIER:
                state = STATE.PRESEDMOUVED_MODIFIER;
                setOrigine(evt.getX(), evt.getY());
                traceModifiedLine();
                break;
            case PRESEDMOUVED_MODIFIER:
                state = STATE.PRESEDMOUVED_MODIFIER;
                setOrigine(evt.getX(), evt.getY());
                traceModifiedLine();
                break;
            case REPOS_SUPPRIMER:
                state = STATE.REPOS_SUPPRIMER;
                break;
            case PRESED_SUPPRIMER:
                state = STATE.PRESED_SUPPRIMER;
                break;       
      }
        }
    }//GEN-LAST:event_formMouseMoved

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        if(init)
        {
        switch(state) {
            case INIT:
                break;            
            case REPOS_AJOUTER:
                break;
            case PRESED_AJOUTER:
                state = STATE.REPOS_AJOUTER;
                setP1(0, 0);
                break;
            case PRESEDMOUVED_AJOUTER:
                state = STATE.REPOS_AJOUTER;
                traceBlacLine();
                break;
            case REPOS_DEPLACER:
                state = STATE.REPOS_DEPLACER;
                break;
            case PRESED_DEPLACER:
                state = STATE.REPOS_DEPLACER;
                setP1(0, 0);
                break;
            case PRESEDMOUVED_DEPLACER:
                state = STATE.REPOS_DEPLACER;
                traceBlacLine();
                break;
            case REPOS_MODIFIER:
                state = STATE.REPOS_MODIFIER;
                break;
            case PRESED_MODIFIER:
                state = STATE.REPOS_MODIFIER;
                setP1(0, 0);
                break;
            case PRESEDMOUVED_MODIFIER:
                state = STATE.REPOS_MODIFIER;
                setOrigine(evt.getX(), evt.getY());
                traceBlacLine();
                break;
            case REPOS_SUPPRIMER:
                state = STATE.REPOS_SUPPRIMER;
                break;
            case PRESED_SUPPRIMER:
                state = STATE.REPOS_AJOUTER;
                //supprimerLine();
                break;
       
        }
        }
    }//GEN-LAST:event_formMouseReleased

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        if(init){    
        switch(state) {
            case INIT:
                state = STATE.INIT;
                break;
            case REPOS_AJOUTER:
                state = STATE.PRESED_AJOUTER;
                setP1(evt.getX(), evt.getY());
                break;
            case PRESED_AJOUTER:
                state = STATE.PRESEDMOUVED_AJOUTER;
                setP2(evt.getX(), evt.getY());
                traceRedLine();
                break;
            case PRESEDMOUVED_AJOUTER:
                state = STATE.PRESEDMOUVED_AJOUTER;
                setP2(evt.getX(), evt.getY());
                traceRedLine();
                break;
            case REPOS_DEPLACER:
                for (Line2D.Float l : lineList) {
                if (l.ptSegDist(evt.getPoint()) < 3.0)
                {
                    state = STATE.PRESED_DEPLACER;
                    p1=l.getP1();
                    p2=l.getP2();
                    originDeplacement = evt.getPoint();
                    deplacedLine = l;
                    deplacerLine(l,p1,p2,originDeplacement);
                }
                }
                break;
            case PRESED_DEPLACER:
                break;
            case PRESEDMOUVED_DEPLACER:
                break;                
            case REPOS_MODIFIER:
                for (Line2D.Float l : lineList) {
                    
                if (l.ptSegDist(evt.getPoint()) < 3.0)
                {
                 
                 state = STATE.PRESED_MODIFIER;
                 p1 = l.getP1();
                 p2 = l.getP2();
                 origin = evt.getPoint();

                 tracer2lines(l,p1,p2);
                 lineList.remove(l);
                 traceModifiedLine();
                }
                     
                }
                break;
            case PRESED_MODIFIER:
                break;
            case PRESEDMOUVED_MODIFIER:
                break;
            case REPOS_SUPPRIMER:
               for (Line2D.Float l : lineList) {
                if (l.ptSegDist(evt.getPoint()) < 3.0)
                {
                 supprimerLine(l);
                }
                }
                break;
            case PRESED_SUPPRIMER:
                throw new RuntimeException("Impossible action");
       }
        }
    }//GEN-LAST:event_formMousePressed

    private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered
       
                
    }//GEN-LAST:event_formMouseEntered

    private void formMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseExited
        
    }//GEN-LAST:event_formMouseExited

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FenetreDeDessin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FenetreDeDessin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FenetreDeDessin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FenetreDeDessin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FenetreDeDessin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAjouter;
    private javax.swing.JButton btnDeplacer;
    private javax.swing.JButton btnModifier;
    private javax.swing.JButton btnSupprimer;
    // End of variables declaration//GEN-END:variables
}
