import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import UserLocation from './user-location';
import UserLocationDetail from './user-location-detail';
import UserLocationUpdate from './user-location-update';
import UserLocationDeleteDialog from './user-location-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={UserLocationDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={UserLocationUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={UserLocationUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={UserLocationDetail} />
      <ErrorBoundaryRoute path={match.url} component={UserLocation} />
    </Switch>
  </>
);

export default Routes;
