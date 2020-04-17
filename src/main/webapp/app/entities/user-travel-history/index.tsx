import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import UserTravelHistory from './user-travel-history';
import UserTravelHistoryDetail from './user-travel-history-detail';
import UserTravelHistoryUpdate from './user-travel-history-update';
import UserTravelHistoryDeleteDialog from './user-travel-history-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={UserTravelHistoryDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={UserTravelHistoryUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={UserTravelHistoryUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={UserTravelHistoryDetail} />
      <ErrorBoundaryRoute path={match.url} component={UserTravelHistory} />
    </Switch>
  </>
);

export default Routes;
