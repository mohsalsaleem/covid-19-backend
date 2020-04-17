import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './user-health-status.reducer';
import { IUserHealthStatus } from 'app/shared/model/user-health-status.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IUserHealthStatusUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const UserHealthStatusUpdate = (props: IUserHealthStatusUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { userHealthStatusEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/user-health-status' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...userHealthStatusEntity,
        ...values
      };

      if (isNew) {
        props.createEntity(entity);
      } else {
        props.updateEntity(entity);
      }
    }
  };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="covid19App.userHealthStatus.home.createOrEditLabel">
            <Translate contentKey="covid19App.userHealthStatus.home.createOrEditLabel">Create or edit a UserHealthStatus</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : userHealthStatusEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="user-health-status-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="user-health-status-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="userIdLabel" for="user-health-status-userId">
                  <Translate contentKey="covid19App.userHealthStatus.userId">User Id</Translate>
                </Label>
                <AvField id="user-health-status-userId" type="text" name="userId" />
              </AvGroup>
              <AvGroup>
                <Label id="severityLabel" for="user-health-status-severity">
                  <Translate contentKey="covid19App.userHealthStatus.severity">Severity</Translate>
                </Label>
                <AvInput
                  id="user-health-status-severity"
                  type="select"
                  className="form-control"
                  name="severity"
                  value={(!isNew && userHealthStatusEntity.severity) || 'NONE'}
                >
                  <option value="NONE">{translate('covid19App.UserHealthSeverity.NONE')}</option>
                  <option value="LOW">{translate('covid19App.UserHealthSeverity.LOW')}</option>
                  <option value="MEDIUM">{translate('covid19App.UserHealthSeverity.MEDIUM')}</option>
                  <option value="HIGH">{translate('covid19App.UserHealthSeverity.HIGH')}</option>
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/user-health-status" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </AvForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  userHealthStatusEntity: storeState.userHealthStatus.entity,
  loading: storeState.userHealthStatus.loading,
  updating: storeState.userHealthStatus.updating,
  updateSuccess: storeState.userHealthStatus.updateSuccess
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(UserHealthStatusUpdate);
