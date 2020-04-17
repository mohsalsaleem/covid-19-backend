import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './user-current-location.reducer';
import { IUserCurrentLocation } from 'app/shared/model/user-current-location.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IUserCurrentLocationUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const UserCurrentLocationUpdate = (props: IUserCurrentLocationUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { userCurrentLocationEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/user-current-location' + props.location.search);
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
        ...userCurrentLocationEntity,
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
          <h2 id="covid19App.userCurrentLocation.home.createOrEditLabel">
            <Translate contentKey="covid19App.userCurrentLocation.home.createOrEditLabel">Create or edit a UserCurrentLocation</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : userCurrentLocationEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="user-current-location-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="user-current-location-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="userIdLabel" for="user-current-location-userId">
                  <Translate contentKey="covid19App.userCurrentLocation.userId">User Id</Translate>
                </Label>
                <AvField id="user-current-location-userId" type="text" name="userId" />
              </AvGroup>
              <AvGroup>
                <Label id="locationIdLabel" for="user-current-location-locationId">
                  <Translate contentKey="covid19App.userCurrentLocation.locationId">Location Id</Translate>
                </Label>
                <AvField id="user-current-location-locationId" type="text" name="locationId" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/user-current-location" replace color="info">
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
  userCurrentLocationEntity: storeState.userCurrentLocation.entity,
  loading: storeState.userCurrentLocation.loading,
  updating: storeState.userCurrentLocation.updating,
  updateSuccess: storeState.userCurrentLocation.updateSuccess
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(UserCurrentLocationUpdate);
