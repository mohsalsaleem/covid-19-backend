import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import UserHealthStatus from './user-health-status';
import UserHealthStatusDetail from './user-health-status-detail';
import UserHealthStatusUpdate from './user-health-status-update';
import UserHealthStatusDeleteDialog from './user-health-status-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={UserHealthStatusDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={UserHealthStatusUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={UserHealthStatusUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={UserHealthStatusDetail} />
      <ErrorBoundaryRoute path={match.url} component={UserHealthStatus} />
    </Switch>
  </>
);

export default Routes;
