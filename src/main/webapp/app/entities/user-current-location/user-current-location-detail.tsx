import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './user-current-location.reducer';
import { IUserCurrentLocation } from 'app/shared/model/user-current-location.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IUserCurrentLocationDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const UserCurrentLocationDetail = (props: IUserCurrentLocationDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { userCurrentLocationEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="covid19App.userCurrentLocation.detail.title">UserCurrentLocation</Translate> [
          <b>{userCurrentLocationEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="userId">
              <Translate contentKey="covid19App.userCurrentLocation.userId">User Id</Translate>
            </span>
          </dt>
          <dd>{userCurrentLocationEntity.userId}</dd>
          <dt>
            <span id="locationId">
              <Translate contentKey="covid19App.userCurrentLocation.locationId">Location Id</Translate>
            </span>
          </dt>
          <dd>{userCurrentLocationEntity.locationId}</dd>
        </dl>
        <Button tag={Link} to="/user-current-location" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/user-current-location/${userCurrentLocationEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ userCurrentLocation }: IRootState) => ({
  userCurrentLocationEntity: userCurrentLocation.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(UserCurrentLocationDetail);
