/*
 * OptionsJPanel.java
 *
 * Created on June 19, 2007, 2:16 AM
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or (at
 * your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 *
 * Copyright (C) 2007 Cheok YanCheng <yccheok@yahoo.com>
 */

package org.yccheok.jstock.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.l2fprod.common.swing.*;

/**
 *
 * @author  yccheok
 */
public class OptionsJPanel extends javax.swing.JPanel implements JStockOptionsObserver {
    
    /** Creates new form OptionsJPanel */
    public OptionsJPanel() {
        initComponents();
        
        initOptionsJPanels();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        buttonGroup1 = new javax.swing.ButtonGroup();
        jButtonBar1 = new JButtonBar(JButtonBar.VERTICAL);

        setLayout(new java.awt.BorderLayout());

        add(jButtonBar1, java.awt.BorderLayout.WEST);

    }// </editor-fold>//GEN-END:initComponents
    
    private void addButton(String title, String iconUrl, final Component component, JButtonBar bar, ButtonGroup group) {
        Action action = new AbstractAction( title, new ImageIcon(OptionsJPanel.class.getResource(iconUrl))) {
            public void actionPerformed(ActionEvent e) {
                show(component);
            }
        };

        JToggleButton button = new JToggleButton(action);
        bar.add(button);

        map.put(title, button);
        
        group.add(button);

        if (group.getSelection() == null) {
            button.setSelected(true);
            show(component);
        }
    }    
    
    private void show(Component component) {        
        if(component == optionsPasswordJPanel) {
            final MainFrame m = (MainFrame)javax.swing.SwingUtilities.getAncestorOfClass(MainFrame.class, this);
            final String password = Utils.decrypt(m.getJStockOptions().getIndicatorPassword());
            
            if(password.length() > 0) {
                PasswordInputJDialog passwordInputJDialog = new PasswordInputJDialog(m, true);
                if(passwordInputJDialog.doModal())
                {
                    if(passwordInputJDialog.isPasswordMatch(password) == false) {
                        JOptionPane.showMessageDialog(this, "Password not match. You are not allow to modify indicator password option.", "Wrong password", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }
                else {
                    // Cancel
                    return;
                }
            }
        }

        if (currentComponent != null) {
            remove(currentComponent);
        }
        
        add("Center", currentComponent = component);
        revalidate();
        repaint();
    }
    
    private void initOptionsJPanels() {
        optionsAlertJPanel = new OptionsAlertJPanel();
        optionsPasswordJPanel = new OptionsPasswordJPanel();
        optionsNetworkJPanel = new OptionsNetworkJPanel();
        optionsSpeedJPanel = new OptionsSpeedJPanel();
        optionsColorJPanel = new OptionsColorJPanel();
        optionsBrokerJPanel = new OptionsBrokerJPanel();
        optionsSellAdvisorJPanel = new OptionsSellAdvisorJPanel();
        optionsChatJPanel = new OptionsChatJPanel();
        optionsUpdateJPanel = new OptionsUpdateJPanel();

        // The size of OptionsJPanel, will be determined by the first added panel.
        addButton("Broker", "/images/32x32/calc.png", optionsBrokerJPanel, jButtonBar1, buttonGroup1);
        addButton("Advisor", "/images/32x32/jabber_protocol.png", optionsSellAdvisorJPanel, jButtonBar1, buttonGroup1);
        addButton("Alert", "/images/32x32/bell.png", optionsAlertJPanel, jButtonBar1, buttonGroup1);
        addButton("Chat", "/images/32x32/ksmiletris.png", optionsChatJPanel, jButtonBar1, buttonGroup1);
        addButton("Color", "/images/32x32/colors.png", optionsColorJPanel, jButtonBar1, buttonGroup1);
        addButton("Network", "/images/32x32/connect_to_network.png", optionsNetworkJPanel, jButtonBar1, buttonGroup1);
        addButton("Password", "/images/32x32/encrypted.png", optionsPasswordJPanel, jButtonBar1, buttonGroup1);
        addButton("Speed", "/images/32x32/clock.png", optionsSpeedJPanel, jButtonBar1, buttonGroup1);         
        addButton("Update", "/images/32x32/epiphany-download.png", optionsUpdateJPanel, jButtonBar1, buttonGroup1);
    }

    public void set(JStockOptions jStockOptions) {
        optionsBrokerJPanel.set(jStockOptions);
        optionsColorJPanel.set(jStockOptions);
        optionsAlertJPanel.set(jStockOptions);
        optionsNetworkJPanel.set(jStockOptions);
        optionsPasswordJPanel.set(jStockOptions);
        optionsSpeedJPanel.set(jStockOptions);       
        optionsSellAdvisorJPanel.set(jStockOptions);
        optionsChatJPanel.set(jStockOptions);
        optionsUpdateJPanel.set(jStockOptions);
    }

    public boolean apply(JStockOptions jStockOptions) {
        /* FIXME : We should make use of JStockOptionsObserver interface. */
        if(optionsBrokerJPanel.apply(jStockOptions) == false)
        {
            final JToggleButton button = map.get("Broker");
            
            button.setSelected(true);
            button.doClick();
            return false;
        }
        
        if(optionsColorJPanel.apply(jStockOptions) == false)
        {
            final JToggleButton button = map.get("Color");
            
            button.setSelected(true);
            button.doClick();
            return false;
        }
        
        if(optionsAlertJPanel.apply(jStockOptions) == false)
        {
            final JToggleButton button = map.get("Alert");
            
            button.setSelected(true);
            button.doClick();
            return false;
        }

        if(optionsPasswordJPanel.apply(jStockOptions) == false)
        {
            final JToggleButton button = map.get("Password");
            
            button.setSelected(true);
            button.doClick();
            return false;
        }
        
        if(optionsNetworkJPanel.apply(jStockOptions) == false)
        {
            final JToggleButton button = map.get("Network");
            
            button.setSelected(true);
            button.doClick();
            return false;
        }
        
        if(optionsSpeedJPanel.apply(jStockOptions) == false)
        {
            final JToggleButton button = map.get("Speed");
            
            button.setSelected(true);
            button.doClick();
            return false;
        }
        
        if(optionsSellAdvisorJPanel.apply(jStockOptions) == false)
        {
            final JToggleButton button = map.get("Advisor");
            
            button.setSelected(true);
            button.doClick();
            return false;
        }

        if(optionsChatJPanel.apply(jStockOptions) == false)
        {
            final JToggleButton button = map.get("Chat");

            button.setSelected(true);
            button.doClick();
            return false;
        }

        if(optionsUpdateJPanel.apply(jStockOptions) == false)
        {
            final JToggleButton button = map.get("Update");

            button.setSelected(true);
            button.doClick();
            return false;
        }
        
        return true;
    }
            
    private Component currentComponent;
    private OptionsAlertJPanel optionsAlertJPanel;
    private OptionsNetworkJPanel optionsNetworkJPanel;
    private OptionsPasswordJPanel optionsPasswordJPanel;
    private OptionsSpeedJPanel optionsSpeedJPanel;
    private OptionsColorJPanel optionsColorJPanel;
    private OptionsBrokerJPanel optionsBrokerJPanel;
    private OptionsSellAdvisorJPanel optionsSellAdvisorJPanel;
    private OptionsChatJPanel optionsChatJPanel;
    private OptionsUpdateJPanel optionsUpdateJPanel;

    private java.util.Map<String, JToggleButton> map = new java.util.HashMap<String, JToggleButton>();
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private com.l2fprod.common.swing.JButtonBar jButtonBar1;
    // End of variables declaration//GEN-END:variables
    
}
