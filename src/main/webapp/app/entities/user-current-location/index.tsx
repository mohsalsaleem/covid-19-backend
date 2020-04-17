import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import UserCurrentLocation from './user-current-location';
import UserCurrentLocationDetail from './user-current-location-detail';
import UserCurrentLocationUpdate from './user-current-location-update';
import UserCurrentLocationDeleteDialog from './user-current-location-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={UserCurrentLocationDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={UserCurrentLocationUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={UserCurrentLocationUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={UserCurrentLocationDetail} />
      <ErrorBoundaryRoute path={match.url} component={UserCurrentLocation} />
    </Switch>
  </>
);

export default Routes;
