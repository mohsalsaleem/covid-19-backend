import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './user-health-status.reducer';
import { IUserHealthStatus } from 'app/shared/model/user-health-status.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IUserHealthStatusDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const UserHealthStatusDetail = (props: IUserHealthStatusDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { userHealthStatusEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="covid19App.userHealthStatus.detail.title">UserHealthStatus</Translate> [<b>{userHealthStatusEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="userId">
              <Translate contentKey="covid19App.userHealthStatus.userId">User Id</Translate>
            </span>
          </dt>
          <dd>{userHealthStatusEntity.userId}</dd>
          <dt>
            <span id="severity">
              <Translate contentKey="covid19App.userHealthStatus.severity">Severity</Translate>
            </span>
          </dt>
          <dd>{userHealthStatusEntity.severity}</dd>
        </dl>
        <Button tag={Link} to="/user-health-status" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/user-health-status/${userHealthStatusEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ userHealthStatus }: IRootState) => ({
  userHealthStatusEntity: userHealthStatus.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(UserHealthStatusDetail);
