// Основные библиотеки
const React = require('react');
const ReactDOM = require('react-dom');

// Для поддержки HAL, URI Templates
const client = require('./client');

// Класс App, загружающий данные по работникам, хранящий
// и отрисовывыющий с помощью компонента EmployeeList
class App extends React.Component {

    // Конструктор, в котором инициализируем состояние пустым массивом, согласно конвенции
    constructor(props) {
        super(props);
        this.state = {employees: []};
    }

    // API, вызываемый после рендеринга React компонента в DOM
    componentDidMount() {
        // массив сотрудников выбирается из бэкэнда Spring Data REST
        // и сохраняется в данных состояния (state) данного компонента.
        client({method: 'GET', path: '/api/employees'}).done(response => {
            this.setState({employees: response.entity._embedded.employees});
        });
    }

    // Рендеринг компонента на экране
    render() {
        return (
            <EmployeeList employees={this.state.employees}/>
        );
    }
}

// Компонент списка сотрудников, представляемого в виде таблицы
class EmployeeList extends React.Component {

    render() {
        const employees = this.props.employees
            .map(employee =>
                <Employee key={employee._links.self.href} employee={employee}/>);

        return (
            <table>
                <tbody>
                <tr>
                    <th>First Name</th>
                    <th>Last Name</th>
                </tr>
                {employees}
                </tbody>
            </table>
        );
    }
}

// Компонент работника, представленный в виде одной строки со столбцами полей сущности
class Employee extends React.Component {

    render() {
        return (
            <tr>
                <td>{this.props.employee.firstName}</td>
                <td>{this.props.employee.lastName}</td>
            </tr>
        );
    }
}

// Рендер всего определенного в компоненте App и в используемых им компонентах в <div> определенном id 'react'
ReactDOM.render(<App/>, document.getElementById('react'));