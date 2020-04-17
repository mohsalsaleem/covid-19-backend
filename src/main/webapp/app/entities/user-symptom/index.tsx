import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import UserSymptom from './user-symptom';
import UserSymptomDetail from './user-symptom-detail';
import UserSymptomUpdate from './user-symptom-update';
import UserSymptomDeleteDialog from './user-symptom-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={UserSymptomDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={UserSymptomUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={UserSymptomUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={UserSymptomDetail} />
      <ErrorBoundaryRoute path={match.url} component={UserSymptom} />
    </Switch>
  </>
);

export default Routes;
