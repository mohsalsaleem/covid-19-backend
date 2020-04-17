import React from 'react';
import MenuItem from 'app/shared/layout/menus/menu-item';
import { DropdownItem } from 'reactstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { Translate, translate } from 'react-jhipster';
import { NavLink as Link } from 'react-router-dom';
import { NavDropdown } from './menu-components';

export const EntitiesMenu = props => (
  <NavDropdown
    icon="th-list"
    name={translate('global.menu.entities.main')}
    id="entity-menu"
    style={{ maxHeight: '80vh', overflow: 'auto' }}
  >
    <MenuItem icon="asterisk" to="/user-current-location">
      <Translate contentKey="global.menu.entities.userCurrentLocation" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/user-location">
      <Translate contentKey="global.menu.entities.userLocation" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/user-health-status">
      <Translate contentKey="global.menu.entities.userHealthStatus" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/user-symptom">
      <Translate contentKey="global.menu.entities.userSymptom" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/user-travel-history">
      <Translate contentKey="global.menu.entities.userTravelHistory" />
    </MenuItem>
    {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
  </NavDropdown>
);
