import React from 'react';
import { Switch } from 'react-router-dom';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import UserCurrentLocation from './user-current-location';
import UserLocation from './user-location';
import UserHealthStatus from './user-health-status';
import UserSymptom from './user-symptom';
import UserTravelHistory from './user-travel-history';
/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({ match }) => (
  <div>
    <Switch>
      {/* prettier-ignore */}
      <ErrorBoundaryRoute path={`${match.url}user-current-location`} component={UserCurrentLocation} />
      <ErrorBoundaryRoute path={`${match.url}user-location`} component={UserLocation} />
      <ErrorBoundaryRoute path={`${match.url}user-health-status`} component={UserHealthStatus} />
      <ErrorBoundaryRoute path={`${match.url}user-symptom`} component={UserSymptom} />
      <ErrorBoundaryRoute path={`${match.url}user-travel-history`} component={UserTravelHistory} />
      {/* jhipster-needle-add-route-path - JHipster will add routes here */}
    </Switch>
  </div>
);

export default Routes;
